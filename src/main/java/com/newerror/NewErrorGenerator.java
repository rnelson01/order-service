package com.newerror;

import exception.HarnessException;
import org.apache.log4j.Logger;

public class NewErrorGenerator {
  private final static Logger logger =
      Logger.getLogger(NewErrorGenerator.class);

  public void generateRunTimeException(String errorMessage) {
    if (errorMessage == null || errorMessage.isEmpty()) {
      errorMessage = "This is a new exception. THIS IS BAD.";
    }
    logger.error("New Error failure encountered",
                 new RuntimeException("New Error encountered"));
    throw new HarnessException(errorMessage);
  }
}
