package timeout;

import org.apache.log4j.Logger;

import java.util.concurrent.TimeoutException;

/**
 * Created by sriram_parthasarathy on 7/6/17.
 */
public class TimeoutExceptionGenerator {

  private final static Logger logger = Logger.getLogger(TimeoutExceptionGenerator.class);

  public static void generateTimeoutException() {
    logger.error("Timeout", new TimeoutException("the system timed out"));
    throw new RuntimeException(new TimeoutException("the system timed out"));
  }

}
