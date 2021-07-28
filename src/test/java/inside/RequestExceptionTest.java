package inside;

import org.junit.Test;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.Assert.*;

public class RequestExceptionTest {
    RequestException req = new RequestException();
    @Test
    public void doGet() throws ServletException, IOException {
        req.doGet(null, null);
        assertTrue(true);
    }

    @Test
    public void getStackTrace() {
        req.getStackTrace(new Throwable());
        assertTrue(true);
    }
}