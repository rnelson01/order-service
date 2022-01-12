package io.harness;

import org.apache.log4j.Logger;

/**
 * Created by rsingh on 7/6/17
 */
public class ArgumentCheker {
  private final static Logger logger = Logger.getLogger(ArgumentCheker.class);

  public void verifyArgument() {
    logger.error("argument verification failed", new IllegalArgumentException("Please refer to the documentation."));
    throw new IllegalArgumentException("Please refer to the documentation.");
  }
}
