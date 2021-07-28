package bookstore;

import org.junit.Test;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.Assert.*;

public class PaymentTest {

    @Test
    public void doGet() throws ServletException, IOException {
        Payment payment = new Payment();
        payment.doGet(null, null);
        assertTrue(true);
    }
}