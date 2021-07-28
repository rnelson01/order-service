package invalid;

import org.apache.log4j.Logger;

import java.io.InvalidClassException;

/**
 * Created by sriram_parthasarathy on 7/6/17.
 */
public class InvalidExceptionGenerator {

  private final static Logger logger = Logger.getLogger(InvalidExceptionGenerator.class);

  public static void generateInvalidActivityException() {
    logger.error("Invalid activity", new RuntimeException("Invalid activity sleep"));
    throw new RuntimeException("Invalid activity sleep");
  }

  public static void generateInvalidClassException() {
    logger.error("Invalid class", new InvalidClassException("com.planets.mars.LifeOnMars"));
    throw new RuntimeException(new InvalidClassException("com.planets.mars.LifeOnMars"));
  }
}
