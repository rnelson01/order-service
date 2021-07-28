package bookstore;


import org.junit.Test;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.Assert.*;

public class InventoryTest {

    @Test
    public void doGet() throws ServletException, IOException {
        Inventory inventory = new Inventory();
        inventory.doGet(null,null);
        assertEquals(1,1);
    }
}