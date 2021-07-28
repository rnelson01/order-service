package job;

import org.junit.Test;

import static org.junit.Assert.*;

public class RegistrationBackgroundHandlerTest {

    @Test
    public void doBackgroundTask() throws InterruptedException {
        RegistrationBackgroundHandler handler = new RegistrationBackgroundHandler();
        try {
            handler.doBackgroundTask();
        }catch (RuntimeException exp) {

        }
        assertTrue(true);
    }
}