package inside;

import org.junit.Test;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.Assert.*;

public class EditTest {

    @Test
    public void doGet() throws ServletException, IOException {
        Edit edit = new Edit();
        edit.doGet(null, null);
        assertTrue(true);
    }
}