package io.heap;

import org.junit.Test;

import static org.junit.Assert.*;

public class MemoryManagerTest {

    @Test(expected = OutOfMemoryError.class)
    public void generate() {
        MemoryManager manager = new MemoryManager();
        manager.generate();
        assertTrue(true);
    }
}