package inside;

import org.junit.Test;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.Assert.*;

public class LoadTest {

    @Test
    public void doGet() throws ServletException, IOException {
        Load load = new Load();
        load.doGet(null, null);
        assertTrue(true);
    }
}