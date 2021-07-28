package register;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import datadog.trace.api.Trace;
import db.ConnectionManager;
import io.prometheus.client.Histogram;
import job.RegistrationBackgroundHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import util.Utilities;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */

public class Register extends HttpServlet {
  private final static Logger logger = Logger.getLogger(Register.class);
  static final Histogram requestLatency =
      Histogram.build()
          .name("register_requests_latency_seconds")
          .help("Request latency in seconds.")
          .register();
  private final static ExecutorService executorService = Executors.newSingleThreadExecutor();
  private final RegistrationBackgroundHandler backgroundHandler = new RegistrationBackgroundHandler();

  @Trace
  @Override
  public void init() throws ServletException {
    try {
      Connection connection = ConnectionManager.getConnection();
      Statement statement = connection.createStatement();
      statement.executeUpdate("create table accounts (name varchar(32),"
                              + " password varchar(32))");
      statement.executeUpdate(
          "create table task (name varchar(32),"
          +
          " thing varchar(60), priority integer, createDate varchar(80),primary key (createDate))");

      // Adding seed users
      statement.executeUpdate("insert into accounts(name,password) values('admin', 'admin')");
      statement.executeUpdate("insert into accounts(name,password) values('john', '1234')");
      statement.executeUpdate("insert into accounts(name,password) values('alice', '1234')");

      statement.close();
    } catch (SQLException e) {
      logger.error("Register init failed", e);
    }
  }

  @Trace
  protected void doPost(HttpServletRequest request,
                        HttpServletResponse response)
      throws ServletException, IOException {
    if( request == null) {
      logger.info("Received null request");
      return;
    }
    if (!isEmpty(System.getenv("peg_cpu")) &&
        System.getenv("peg_cpu").equals("true")) {
      Utilities.pegCpu();
    }
    Histogram.Timer requestTimer = requestLatency.startTimer();
    String name = request.getParameter("name");
    String password = request.getParameter("password");
    String password2 = request.getParameter("password2");
    executorService.submit(() -> {
		try {
			backgroundHandler.doBackgroundTask();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	});

    if (!isEmpty(request.getParameter("forwardTo"))) {
      logger.info("will forward the request to another tier");
      HttpClient client = new DefaultHttpClient();
      HttpGet httpGet = new HttpGet(request.getParameter("forwardTo"));
      HttpResponse httpResponse = client.execute(httpGet);
      logger.info("sending request to other tier " +
                  httpResponse.getStatusLine().getStatusCode());
      return;
    } else {
      logger.info("Not going to forward");
    }
    boolean throwError =
        StringUtils.isBlank(request.getParameter("throwError"))
            ? false
            : Boolean.parseBoolean(request.getParameter("throwError"));

    if (throwError) {
      throw new RuntimeException("fail on purpose");
    }

    boolean exists = false;

    try {
      Connection connection = ConnectionManager.getConnection();

      Statement statement = connection.createStatement();

      ResultSet resultSet = statement.executeQuery("select name from accounts");

      while (resultSet.next()) {
        if (resultSet.getString(1).equals(name))
          exists = true;
      }
      resultSet.close();
      statement.close();

      if (password == null || password.isEmpty() || name == null ||
          name.isEmpty() || !password.equals(password2)) {
        response.sendRedirect(request.getContextPath() + "/wrongRegister.jsp");
      } else if (exists) {
        response.sendRedirect(request.getContextPath() + "/userExists.jsp");
      } else {
        try {
          connection = ConnectionManager.getConnection();
          PreparedStatement preparedStatement = connection.prepareStatement(
              "insert into accounts(name,password) values(?, ?)");

          preparedStatement.setString(1, name);
          preparedStatement.setString(2, password);

          preparedStatement.executeUpdate();

          preparedStatement.close();
        } catch (SQLException e) {
          logger.error("Another failure", e);
        } catch (Exception e) {
          logger.error("ERROR:  failed to load HSQLDB JDBC driver", e);
        }
        response.sendRedirect(request.getContextPath() + "/login.jsp");
      }
    } catch (SQLException e) {
      logger.error("Register doPost failed", e);
    } catch (Exception e) {
      logger.error("ERROR: failed to load HSQLDB JDBC driver!", e);
    } finally {
      requestTimer.observeDuration();
    }
  }
}
