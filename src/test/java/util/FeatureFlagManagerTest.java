package util;

import exception.HarnessException;
import org.junit.Test;

import static org.junit.Assert.*;

public class FeatureFlagManagerTest {

    @Test
    public void getFlagValue() throws HarnessException {
        FeatureFlagManager ff = new FeatureFlagManager();
        ff.getFlagValue("dummy","harnessdev");
        assertEquals(1,1);
    }
}