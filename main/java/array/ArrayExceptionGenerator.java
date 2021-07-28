package array;

import org.apache.log4j.Logger;

/**
 * Created by sriram_parthasarathy on 7/6/17.
 */
public class ArrayExceptionGenerator {

  private final static Logger logger = Logger.getLogger(ArrayExceptionGenerator.class);

  public static void generateIndexOutOfBoundsException() {
    logger.error("Index out of bounds", new ArrayIndexOutOfBoundsException("Index -1 out of bounds"));
    throw new ArrayIndexOutOfBoundsException("Index -1 out of bounds");
  }
}
