package healthcheck;

import org.junit.Test;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.Assert.*;

public class HealthCheckTest {

    @Test
    public void doGet() throws ServletException, IOException {
        HealthCheck hc = new HealthCheck();
        hc.doGet(null, null);
        assertTrue(true);
    }
}