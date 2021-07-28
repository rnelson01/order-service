package file;

import inside.RequestException;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.nio.file.FileSystemException;

/**
 * Created by sriram_parthasarathy on 7/6/17.
 */
public class FileExecptionGenerator {
  private final static Logger logger = Logger.getLogger(RequestException.class);

  public void generateFileSystemException() {
    logger.error("file system exception", new FileSystemException("file system is corrupted"));
    throw new RuntimeException(new FileSystemException("file system is corrupted"));
  }

  public void generateFileNotFoundException() {
    logger.error("file not found exception", new FileNotFoundException("could not find the file"));
    throw new RuntimeException(new FileNotFoundException("could not find the file"));
  }
}
