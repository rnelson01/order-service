package org.state;

import org.junit.Test;

import static org.junit.Assert.*;

public class StateMachineTest {

    @Test(expected = IllegalStateException.class)
    public void executeState() {
        StateMachine sm = new StateMachine();
        sm.executeState();
        assertTrue(true);
    }
}