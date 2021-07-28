package db;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by peeyushaggarwal on 9/7/16.
 */
public class ConnectionManager {
  private final static Logger logger = Logger.getLogger(ConnectionManager.class);
  private static final Connection connection;
  static {
    try {
      Class.forName("org.h2.Driver");
    } catch (Exception e) {
      logger.error("ConnectionManager: Driver not found ", e);
      throw new RuntimeException(e);
    }
    try {
      connection = DriverManager.getConnection("jdbc:h2:mem:list;MODE=MYSQL", "sa", "");
    } catch (SQLException e) {
      logger.error("ConnectionManager: Unable to connect ", e);
      throw new RuntimeException(e);
    }
  }

  public static synchronized Connection getConnection() {
    return connection;
  }
}
