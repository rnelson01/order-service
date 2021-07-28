package inside;

import db.ConnectionManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Delete
 */

public class Delete extends HttpServlet {
  private final static Logger logger = Logger.getLogger(Delete.class);
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    if( request == null) {
      logger.info("Received null request");
      return;
    }
    String date = request.getParameter("date");
    //		System.out.println("the parameter is " + date);
    try {
      Connection connection = ConnectionManager.getConnection();


      String queryString = "delete from task where createDate = ?";
      PreparedStatement statement = connection.prepareStatement(queryString);
      statement.setString(1, date);
      statement.executeUpdate();
      statement.close();
    } catch (SQLException e) {
      logger.error("Failure in delete", e);
    }
    response.sendRedirect("/todolist/inside/display");
  }
}
