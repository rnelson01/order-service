package bookstore;

import java.io.IOException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by rsingh on 5/18/18.
 */
public class Inventory extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Random r = new Random();
    try {
      Thread.sleep(r.nextInt(100));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
