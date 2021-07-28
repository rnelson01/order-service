package login;

import org.junit.Test;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.Assert.*;

public class LoginTest {

    @Test
    public void doGet() throws ServletException, IOException {
        Login login = new Login();
        login.doGet(null, null);
        assertTrue(true);
    }
}