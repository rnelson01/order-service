package bookstore;

import org.junit.Test;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.Assert.*;

public class SynchronousProcessingTest {

    @Test
    public void doGet() throws ServletException, IOException {
        SynchronousProcessing sp = new SynchronousProcessing();
        sp.doGet(null,null);
        assertTrue(true);
    }
}