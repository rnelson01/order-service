package inside;

import org.junit.Test;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.Assert.*;

public class DisplayTest {

    @Test
    public void doGet() throws ServletException, IOException {
        Display display = new Display();
        display.doGet(null, null);
        assertTrue(true);
    }
}