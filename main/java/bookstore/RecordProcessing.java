package bookstore;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by rsingh on 5/18/18.
 */
public class RecordProcessing extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    if(req == null || resp == null) {
      return;
    }
    Random r = new Random();
    try {
      if (!StringUtils.isEmpty(req.getParameter("generateError"))) {
        Thread.sleep(r.nextInt(2000));
      } else {
        Thread.sleep(r.nextInt(100));
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
