package bookstore;

import org.junit.Test;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.Assert.*;

public class RecordProcessingTest {

    @Test
    public void doGet() throws ServletException, IOException {
        RecordProcessing rp = new RecordProcessing();
        rp.doGet(null, null);
        assertTrue(true);
    }
}