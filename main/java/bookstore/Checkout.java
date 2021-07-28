package bookstore;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by rsingh on 5/18/18.
 */
public class Checkout extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if (System.getProperty("inventory_url") != null) {
      HttpClient client = new DefaultHttpClient();
      HttpGet httpGet = new HttpGet(System.getProperty("inventory_url"));
      HttpResponse httpResponse = client.execute(httpGet);
      if(httpResponse.getStatusLine().getStatusCode() != 200) {
        throw new IllegalStateException("invalid code " + httpResponse.getStatusLine().getStatusCode());
      }
    }

    if (System.getProperty("payment_url") != null) {
      HttpClient client = new DefaultHttpClient();
      HttpGet httpGet = new HttpGet(System.getProperty("payment_url"));
      HttpResponse httpResponse = client.execute(httpGet);
      if(httpResponse.getStatusLine().getStatusCode() != 200) {
        throw new IllegalStateException("invalid code " + httpResponse.getStatusLine().getStatusCode());
      }
    }
  }
}
