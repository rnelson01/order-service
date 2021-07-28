package io.harness;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArgumentChekerTest {

    @Test(expected = IllegalArgumentException.class)
    public void verifyArgument() {
        ArgumentCheker cheker = new ArgumentCheker();
        cheker.verifyArgument();
        assertTrue(true);
    }
}