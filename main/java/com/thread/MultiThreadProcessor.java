package com.thread;

import org.apache.log4j.Logger;

import java.util.ConcurrentModificationException;

/**
 * Created by rsingh on 7/6/17.
 */
public class MultiThreadProcessor {

  private final static Logger logger = Logger.getLogger(MultiThreadProcessor.class);

  public void process() {
    logger.error("someone interrupted me", new InterruptedException("please let me do my stuff"));
    throw new RuntimeException(new InterruptedException("please let me do my stuff"));
  }

  public void processConcurrent() {
    logger.error("don't modify while iterating", new ConcurrentModificationException("concurrent modification detected"));
    throw new ConcurrentModificationException("concurrent modification detected");
  }
}
