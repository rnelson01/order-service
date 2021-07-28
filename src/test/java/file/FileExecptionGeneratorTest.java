package file;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.nio.file.FileSystemException;

import static org.junit.Assert.*;

public class FileExecptionGeneratorTest {
    FileExecptionGenerator generator = new FileExecptionGenerator();

    @Test(expected = RuntimeException.class)
    public void generateFileSystemException() {
        generator.generateFileSystemException();
        assertTrue(true);
    }

    @Test(expected = RuntimeException.class)
    public void generateFileNotFoundException() {
        generator.generateFileNotFoundException();
        assertTrue(true);
    }
}