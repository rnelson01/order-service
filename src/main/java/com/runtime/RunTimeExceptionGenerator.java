package com.runtime;

import datadog.trace.api.Trace;
import org.apache.log4j.Logger;

/**
 * Created by rsingh on 7/6/17.
 */
public class RunTimeExceptionGenerator {

  private final static Logger logger = Logger.getLogger(RunTimeExceptionGenerator.class);

  @Trace
  public void generateRunTimeException() {
    logger.error("Runtime failure encountered", new RuntimeException("Method throws runtime exception"));
    throw new RuntimeException("Method throws runtime exception");
  }
}
