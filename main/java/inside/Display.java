package inside;

import org.apache.log4j.Logger;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Display
 */

public class Display extends HttpServlet {
  private final static Logger logger = Logger.getLogger(Display.class);
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    logger.info("Handling display request");
    if( request == null) {
      logger.info("Received null request");
      return;
    }
    response.sendRedirect("display.jsp");
  }
}
