package register;

import org.junit.Test;

import javax.servlet.ServletException;

import java.io.IOException;

import static org.junit.Assert.*;

public class RegisterTest {

    Register register = new Register();
    @Test
    public void init() throws ServletException {
        register.init();
        assertTrue(true);
    }

    @Test
    public void doPost() throws ServletException, IOException {
        register.doPost(null, null);
        assertTrue(true);
    }
}