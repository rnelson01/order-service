package io;

import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created by sriram_parthasarathy on 7/6/17.
 */
public class IOExceptionGenerator {

  private final static Logger logger = Logger.getLogger(IOExceptionGenerator.class);

  public static void generateIOException() {
    logger.error("IO Error", new IOException("Error reading and writing to file"));
    throw new RuntimeException(new IOException("Error reading and writing to file"));
  }
}
