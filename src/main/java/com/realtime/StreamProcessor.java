package com.realtime;

import org.apache.log4j.Logger;

/**
 * Created by rsingh on 7/6/17.
 */
public class StreamProcessor {
  private final static Logger logger = Logger.getLogger(StreamProcessor.class);

  public void processStreamData() {
    logger.error("Stream processing failed", new NullPointerException("Null pointer exception"));
    throw new NullPointerException("Null pointer exception");
  }
}
