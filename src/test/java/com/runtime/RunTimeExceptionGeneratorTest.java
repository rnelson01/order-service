package com.runtime;

import org.junit.Test;

import static org.junit.Assert.*;

public class RunTimeExceptionGeneratorTest {

    @Test(expected = RuntimeException.class)
    public void generateRunTimeException() {
        RunTimeExceptionGenerator generator = new RunTimeExceptionGenerator();
        generator.generateRunTimeException();
        assertTrue(true);
    }
}