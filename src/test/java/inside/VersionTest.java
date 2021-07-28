package inside;

import org.junit.Test;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.Assert.*;

public class VersionTest {

    @Test
    public void doGet() throws ServletException, IOException {
        Version version = new Version();
        version.doGet(null, null);
        assertTrue(true);
    }
}