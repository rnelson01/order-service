package io;

import org.junit.Test;

import static org.junit.Assert.*;

public class IOExceptionGeneratorTest {

    @Test(expected = RuntimeException.class)
    public void generateIOException() {
        IOExceptionGenerator generator = new IOExceptionGenerator();
        generator.generateIOException();
        assertTrue(true);
    }
}