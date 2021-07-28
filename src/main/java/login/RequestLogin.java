package login;

import db.ConnectionManager;
import io.prometheus.client.Counter;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestLogin
 */
@WebServlet("/RequestLogin")
public class RequestLogin extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private final static Logger logger = Logger.getLogger(RequestLogin.class);
  static final Counter requests = Counter.build()
      .name("requests_total").help("Total requests.").register();


  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    if( request == null) {
      logger.info("Received null request");
      return;
    }
    String name = request.getParameter("name");
    String password = request.getParameter("password");
    String remember = request.getParameter("remember");
    boolean success = false;
    if (password == null || password.isEmpty() || name == null || name.isEmpty()) {
      response.sendRedirect(request.getContextPath() + "/loginFault.jsp");
    } else {
      try {

        Connection connection = ConnectionManager.getConnection();

        //				System.out.println("connection done");

        Statement statement = connection.createStatement();

        //				System.out.println("WTF?");

        ResultSet resultSet = statement.executeQuery("select name, password from accounts");

        //				System.out.println("nima");

        while (resultSet.next()) {
          if (resultSet.getString(1).equals(name)) {
            if (resultSet.getString(2).equals(password)) {
              success = true;
              break;
            }
          }
        }
        resultSet.close();
        statement.close();
        if (success) {
          request.getSession().setAttribute("name", name);
          if (remember == null) {
            request.getSession().setMaxInactiveInterval(1200);
          } else {
            request.getSession().setMaxInactiveInterval(86400 * 7);
          }
          response.sendRedirect(request.getContextPath() + "/inside/display");
        } else {
          response.sendRedirect(request.getContextPath() + "/loginFault.jsp");
        }
      } catch (SQLException e) {
        logger.error("Failure in login", e);
      } catch (Exception e) {
        logger.error("Failure in login", e);
      }
    }
  }
}
