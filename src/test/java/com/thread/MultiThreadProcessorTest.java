package com.thread;

import org.junit.Test;

import java.util.ConcurrentModificationException;

import static org.junit.Assert.*;

public class MultiThreadProcessorTest {

    MultiThreadProcessor processor = new MultiThreadProcessor();
    @Test(expected = RuntimeException.class)
    public void process() {
        processor.process();
        assertTrue(true);
    }

    @Test(expected = ConcurrentModificationException.class)
    public void processConcurrent() {
        processor.processConcurrent();
        assertTrue(true);
    }
}