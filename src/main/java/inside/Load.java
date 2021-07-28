package inside;

import com.newrelic.api.agent.NewRelic;
import datadog.trace.api.Trace;
import db.ConnectionManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Random;
import org.apache.log4j.Logger;

public class Load extends HttpServlet {

    private final static Logger logger = Logger.getLogger(Load.class);
    private static final long serialVersionUID = 1L;
    private static final int maxNoOfTransactions = 200;
    private static final int minNoOfTransactions = 1;
    Random r = new Random();

    private int getRandomNumber() {
        return minNoOfTransactions + r.nextInt(maxNoOfTransactions);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Trace
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if( request == null) {
            logger.info("Received null request");
            return;
        }
        NewRelic.setTransactionName(null, "/load test/" + getRandomNumber());
        String task = request.getParameter("task");
        String pri = request.getParameter("priority");
        int priority;

        if (task.isEmpty() || !pri.matches("[1-9][0-9]*"))
            response.sendRedirect("/todolist/inside/display");
        else {
            priority = Integer.parseInt(pri);
            String name = "Admin"; //(String) (request.getSession(false).getAttribute("name"));
            Date date = new Date();

            try {
                Connection connection = ConnectionManager.getConnection();
                String queryString = "insert into task values(?,?,?,?)";
                PreparedStatement statement = connection.prepareStatement(queryString);
                statement.setString(1, name);
                statement.setString(2, task);
                statement.setString(3, Integer.toString(priority));
                statement.setString(4, date.toString().replace(' ', '_'));
                statement.executeUpdate();
                statement.close();
            } catch (SQLException e) {
                logger.error("Failure in add task", e);
            }
            response.sendRedirect("/todolist/inside/display");
        }
    }
}
