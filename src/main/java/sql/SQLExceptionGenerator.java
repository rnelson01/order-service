package sql;

import org.apache.log4j.Logger;

import java.util.concurrent.TimeoutException;

/**
 * Created by sriram_parthasarathy on 7/6/17.
 */
public class SQLExceptionGenerator {

  private final static Logger logger = Logger.getLogger(SQLExceptionGenerator.class);

  public static void generateSQLDataException() {
    logger.error("sql data exception", new TimeoutException("SQL data missing columns"));
    throw new RuntimeException(new TimeoutException("SQL data missing columns"));
  }
}
