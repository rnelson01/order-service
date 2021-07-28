package invalid;

import org.junit.Test;

import static org.junit.Assert.*;

public class InvalidExceptionGeneratorTest {
    InvalidExceptionGenerator generator = new InvalidExceptionGenerator();
    @Test(expected = RuntimeException.class)
    public void generateInvalidActivityException() throws InterruptedException {
        Thread.sleep(10 * 1000); //10sec
        generator.generateInvalidActivityException();
        assertTrue(true);
    }

    @Test(expected = RuntimeException.class)
    public void generateInvalidClassException() {
        generator.generateInvalidClassException();
        assertTrue(true);
    }
}