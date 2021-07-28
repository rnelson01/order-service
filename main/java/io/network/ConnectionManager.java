package io.network;

import org.apache.log4j.Logger;

import java.net.SocketTimeoutException;

/**
 * Created by rsingh on 7/6/17.
 */
public class ConnectionManager {
  private final static Logger logger = Logger.getLogger(ConnectionManager.class);

  public void connect() {
    logger.error("Service is down. Timeout", new SocketTimeoutException("check the network"));
  }
}
