package inside;

import array.ArrayExceptionGenerator;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.newerror.NewErrorGenerator;
import com.realtime.StreamProcessor;
import com.runtime.RunTimeExceptionGenerator;
import com.thread.MultiThreadProcessor;
import datadog.trace.api.Trace;
import exception.HarnessException;
import file.FileExecptionGenerator;
import invalid.InvalidExceptionGenerator;
import io.IOExceptionGenerator;
import io.harness.ArgumentCheker;
import io.heap.MemoryManager;
import io.network.ConnectionManager;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.state.StateMachine;
import sql.SQLExceptionGenerator;
import timeout.TimeoutExceptionGenerator;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * Created by sriram_parthasarathy on 7/6/17.
 */
public class RequestException extends HttpServlet {
  private static final String SOURCE_NAME_SUFFIX = UUID.randomUUID().toString();
  private final static Logger logger = Logger.getLogger(RequestException.class);
  public static final MediaType JSON
      = MediaType.parse("application/json; charset=utf-8");
  private AtomicInteger count = new AtomicInteger();
  private int numOfException = 15;
  private Random r = new Random();
  CloseableHttpClient httpclient = HttpClients.createDefault();
  boolean throwError;

  public static void main(String[] arga) {
    int i = 0;
    RequestException e = new RequestException();
    while (i < 5000) {
      i++;
      try {
        e.doGet(null, null);
        Thread.sleep(1000);
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }


  @Override
  @Trace
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    if( req == null) {
      logger.info("Received null request");
      return;
    }

    try {

      int seq = count.incrementAndGet();

      throwError = StringUtils.isBlank(req.getParameter("throwError")) ? false : Boolean.parseBoolean(req.getParameter("throwError"));

      if (throwError) {
        new NewErrorGenerator().generateRunTimeException(req.getParameter("errorMessage"));
      }
      int mod = seq % numOfException;
      switch (mod) {
        case 0:
          new RunTimeExceptionGenerator().generateRunTimeException();
          break;
        case 1:
          new StateMachine().executeState();
          break;
        case 2:
          new ArgumentCheker().verifyArgument();
          break;
        case 3:
          new StreamProcessor().processStreamData();
          break;
        case 4:
          new ConnectionManager().connect();
          break;
        case 5:
          new MultiThreadProcessor().process();
          break;
        case 6:
          new MultiThreadProcessor().processConcurrent();
          break;
        case 7:
          new MemoryManager().generate();
          break;
        case 8:
          new FileExecptionGenerator().generateFileSystemException();
          break;
        case 9:
          new FileExecptionGenerator().generateFileNotFoundException();
          break;
        case 10:
          IOExceptionGenerator.generateIOException();
          break;
        case 11:
          InvalidExceptionGenerator.generateInvalidActivityException();
          break;
        case 12:
          InvalidExceptionGenerator.generateInvalidClassException();
          break;
        case 13:
          ArrayExceptionGenerator.generateIndexOutOfBoundsException();
          break;
        case 14:
          TimeoutExceptionGenerator.generateTimeoutException();
          break;
        default:
          SQLExceptionGenerator.generateSQLDataException();
      }
    } catch (HarnessException he) {
    	throw he;
	} catch (Throwable t) {
      String level = new Random().nextBoolean() ? "ERROR" : "WARN";
      logger.error("Error ", t);
      if (System.getProperty("sumo.url") != null) {
        logger.info("sumo url is " + System.getProperty("sumo.url"));
        HttpPost httpPost = new HttpPost(System.getProperty("sumo.url"));
        String out = getStackTrace(t).replaceAll("\n", " ").replaceAll("\t", " ");
        httpPost.setEntity(new StringEntity(out));
        httpPost.setHeader("X-Sumo-Host", InetAddress.getLocalHost().getHostName());
        logger.info("sending log exception to sumo ");
        CloseableHttpResponse response = httpclient.execute(httpPost);
        logger.info("status log sumo exception: " + response.getStatusLine());
        response.close();
      } else {
        logger.info("sumo url not set");
      }
      if (System.getProperty("sumo.url.sourcename") != null) {
        HttpPost httpPost = new HttpPost(System.getProperty("sumo.url.sourcename"));
        httpPost.setEntity(new StringEntity(getStackTrace(t)));
        httpPost.setHeader("X-Sumo-Name", "default." + InetAddress.getLocalHost().getHostName() + "." + SOURCE_NAME_SUFFIX);
        logger.info("sending log exception to sumo ");
        CloseableHttpResponse response = httpclient.execute(httpPost);
        logger.info("status log sumo exception: " + response.getStatusLine());
        response.close();
      }
      if (System.getProperty("splunk_url") != null) {
        logger.info("Sending data to splunk url " + System.getProperty("splunk_url"));
        try {
          String out = "{\"sourcetype\": \"todolist\", \"host\": \"" + InetAddress.getLocalHost().getHostName() + "\" ,\"event\":" + "\""
              + getStackTrace(t) + "\"" + "}";
          out = out.replaceAll("\n"," ").replaceAll("\t", " ");

          RequestBody body = RequestBody.create(JSON, out);

          Request request = new Request.Builder()
              .url(System.getProperty("splunk_url"))
              .addHeader("Authorization",
                  "Splunk " + System.getProperty("splunk_token"))
              .post(body)
              .build();

          OkHttpClient.Builder builder = getUnsafeOkHttpClient();
          OkHttpClient client = builder.build();
          Response r = client.newCall(request).execute();
          System.out.println("Splunk response " + r.body().string());
          logger.info("Sent data to splunk url " + System.getProperty("splunk_url"));
        } catch (Exception ex) {
          logger.error("Exception while storing data into splunk", ex);
        }
      }

      if (System.getProperty("elk_url") != null) {
        logger.info("elk url is " + System.getProperty("elk_url"));
        String s = getStackTrace(t);
        LogData logData = new LogData();
        logData.hostname = InetAddress.getLocalHost().getHostName();
        logData.level = level;
        logData.message = s;

        Gson gson = new Gson();
        JsonElement jsonElement = gson.toJsonTree(logData);
        JsonObject jsonObject = (JsonObject) jsonElement;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        Date now = new Date();
        jsonObject.addProperty("@timestamp", "" + sdf.format(now));

        String out = jsonObject.toString();
        out = out.replaceAll("\n", " ").replaceAll("\t", " ");
        HttpPost httpPost = new HttpPost(System.getProperty("elk_url"));
        httpPost.setEntity(new StringEntity(out, ContentType.APPLICATION_JSON));
        httpPost.setHeader("hostname", InetAddress.getLocalHost().getHostName());
        httpPost.setHeader("Content-type", "application/json");
        logger.info("sending request to elk server: " + System.getProperty("elk_url"));
        CloseableHttpResponse response = httpclient.execute(httpPost);
        logger.info("status log elk exception: " + response.getStatusLine());
        response.close();
      } else {
        logger.info("elk url not set");
      }

      if (System.getProperty("logz_url") != null) {
        String s = getStackTrace(t);
        String out = "{\"hostname\": \"" + InetAddress.getLocalHost().getHostName() + "\", \"message\":" + "\"" + s + "\"" + "}";
        out = out.replaceAll("\n", " ").replaceAll("\t", " ");

        HttpPost httpPost = new HttpPost(System.getProperty("logz_url"));
        httpPost.setEntity(new StringEntity(out, ContentType.APPLICATION_JSON));
        httpPost.setHeader("hostname", InetAddress.getLocalHost().getHostName());
        httpPost.setHeader("Content-type", "application/json");
        CloseableHttpResponse response = httpclient.execute(httpPost);
        logger.info("status log logz exception: " + response.getStatusLine());
        response.close();
      }

      if (System.getProperty("scalyr_token") != null) {
        logger.info("Sending data to scalyr token " + System.getProperty("scalyr_token"));
        final String scalyr_token = System.getProperty("scalyr_token");
        String scalyrUrl = "https://app.scalyr.com/addEvents";
        try {
          String sessionInfo="{\"pod_id\": \"" + InetAddress.getLocalHost().getHostName() + "\", \"container_type\": \"POD\", \"source_type\": \"todolist\"}";
          String attr = "{\n" +
                  "  \"message\": \"" + getStackTrace(t).replaceAll("\n", " ") +"\",\n" +
                  "  \"message_backup\": \"" + getStackTrace(t).replaceAll("\n", " ") +"\",\n" +
                  "  \"length\": " + getStackTrace(t).length() + "\n" +
                  "}";
          String event = "{\n" +
                  "  \"thread\": \"todolist-" + Thread.currentThread().getName() + "\",\n" +
                  "  \"ts\": \"" + TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis()) + "\",\n" +
                  "  \"sev\": 5,\n" +
                  "  \"attrs\": " + attr + "\n" +
                  "}";
          String out = "{\n" +
                  "  \"token\":           \"" + scalyr_token + "\",\n" +
                  "  \"session\":         \"todolist\",\n" +
                  "  \"sessionInfo\":     " + sessionInfo + ",\n" +
                  "  \"events\":          [" + event + "]\n" +
                  "}";
          logger.info("to be sent: " + out);
          HttpPost httpPost = new HttpPost(scalyrUrl);
          httpPost.setEntity(new StringEntity(out, ContentType.APPLICATION_JSON));
          httpPost.setHeader("Content-type", "application/json");
          CloseableHttpResponse response = httpclient.execute(httpPost);
          logger.info("status log scalyr exception: " + response.getStatusLine());
          response.close();
        } catch (Exception ex) {
          logger.error("Exception while storing data into scalyr", ex);
        }
      }
    }
    try {
      Thread.sleep(r.nextInt(20));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }

  private static OkHttpClient.Builder getUnsafeOkHttpClient() {
    try {
      // Create a trust manager that does not validate certificate chains
      final TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager(){
        @Override public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType)
            throws CertificateException {}

        @Override public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType)
            throws CertificateException {}

        @Override public java.security.cert.X509Certificate[] getAcceptedIssuers(){
          return new X509Certificate[] {};
        }
      }
      };

      // Install the all-trusting trust manager
      final SSLContext sslContext = SSLContext.getInstance("SSL");
      sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
      // Create an ssl socket factory with our all-trusting manager
      final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

      OkHttpClient.Builder builder = new OkHttpClient.Builder();
      builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
      builder.hostnameVerifier((hostname, session) -> true);

      return builder;
    }
    catch (Exception e) {

    }
    return null;
  }

  public static String getStackTrace(Throwable aThrowable) {
    Writer result = new StringWriter();
    PrintWriter printWriter = new PrintWriter(result);
    aThrowable.printStackTrace(printWriter);
    return result.toString();
  }

  static class LogData {
    String hostname;
    String level;
    String message;
  }
}
