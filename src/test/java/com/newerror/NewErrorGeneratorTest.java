package com.newerror;

import exception.HarnessException;
import org.junit.Test;

import static org.junit.Assert.*;

public class NewErrorGeneratorTest {

    @Test(expected = HarnessException.class)
    public void generateRunTimeException() {
        NewErrorGenerator generator = new NewErrorGenerator();
        generator.generateRunTimeException("harness");
        assertTrue(true);
    }
}