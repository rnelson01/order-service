package timeout;

import org.junit.Test;

import static org.junit.Assert.*;

public class TimeoutExceptionGeneratorTest {

    @Test(expected = RuntimeException.class)
    public void generateTimeoutException() {
        TimeoutExceptionGenerator generator = new TimeoutExceptionGenerator();
        generator.generateTimeoutException();
        assertTrue(true);
    }
}