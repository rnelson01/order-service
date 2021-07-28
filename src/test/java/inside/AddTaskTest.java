package inside;

import org.junit.Test;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.Assert.*;

public class AddTaskTest {

    @Test
    public void doGet() throws ServletException, IOException {
        AddTask add = new AddTask();
        add.doGet(null,null);
        assertTrue(true);
    }
}