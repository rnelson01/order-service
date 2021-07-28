package bookstore;

import org.junit.Test;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.Assert.*;

public class CheckoutTest {

    @Test
    public void doGet() throws ServletException, IOException {
        Checkout checkout = new Checkout();
        checkout.doGet(null,null);
        assertTrue(true);
    }
}