package login;

import org.junit.Test;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.Assert.*;

public class RequestLoginTest {

    @Test
    public void doPost() throws ServletException, IOException {
        RequestLogin login = new RequestLogin();
        login.doPost(null, null);
        assertTrue(true);
    }
}