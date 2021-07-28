package org.state;

import org.apache.log4j.Logger;

/**
 * Created by rsingh on 7/6/17.
 */
public class StateMachine {
  private final static Logger logger = Logger.getLogger(StateMachine.class);

  public void executeState() {
    logger.error("Misconfigured state", new IllegalStateException("Unknown parameters in state"));
    throw  new IllegalStateException("Unknown parameters in state");
  }
}
