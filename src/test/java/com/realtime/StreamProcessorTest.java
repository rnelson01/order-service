package com.realtime;

import org.junit.Test;

import static org.junit.Assert.*;

public class StreamProcessorTest {

    @Test(expected = NullPointerException.class)
    public void processStreamData() {
        StreamProcessor sp = new StreamProcessor();
        sp.processStreamData();
        assertTrue(true);
    }
}