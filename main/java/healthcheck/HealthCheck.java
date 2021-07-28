package healthcheck;

import db.ConnectionManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by peeyushaggarwal on 9/19/16.
 */
public class HealthCheck extends HttpServlet {
  private final static Logger logger = Logger.getLogger(HealthCheck.class);

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if( req == null) {
      logger.info("Received null request");
      return;
    }
    logger.info("Handling healthcheck request");
    resp.setStatus(200);
    resp.setContentType("application/xml");
    resp.getOutputStream().println("<health-check><status>OK</status></health-check>");

  }
}
