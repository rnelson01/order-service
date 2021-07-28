package io.heap;

import org.apache.log4j.Logger;

/**
 * Created by rsingh on 7/6/17.
 */
public class MemoryManager {
  private final static Logger logger = Logger.getLogger(MemoryManager.class);

  public void generate() {
    logger.error("OOM detected", new OutOfMemoryError("JVM out of memory!!"));
    throw new OutOfMemoryError("JVM out of memory!!");
  }
}
