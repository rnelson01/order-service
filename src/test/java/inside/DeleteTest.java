package inside;

import org.junit.Test;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.Assert.*;

public class DeleteTest {

    @Test
    public void doGet() throws ServletException, IOException, InterruptedException {
        Delete delete = new Delete();
        delete.doGet(null, null);
        Thread.sleep(6000);
        assertTrue(true);
    }
}