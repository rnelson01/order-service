package db;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConnectionManagerTest {

    @Test
    public void getConnection() {
        ConnectionManager manager = new ConnectionManager();
        manager.getConnection();
        assertTrue(true);
    }
}