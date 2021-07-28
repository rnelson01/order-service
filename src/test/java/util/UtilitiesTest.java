package util;

import org.junit.Test;
import static org.junit.Assert.*;


public class UtilitiesTest {
    @Test
    public void pegCpu() {
        Utilities utils = new Utilities();
        utils.pegCpu(1);
        assertEquals(1,1);
    }
}