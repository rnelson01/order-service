package sql;

import org.junit.Test;

import static org.junit.Assert.*;

public class SQLExceptionGeneratorTest {

    @Test(expected = RuntimeException.class)
    public void generateSQLDataException() {
        SQLExceptionGenerator generator = new SQLExceptionGenerator();
        generator.generateSQLDataException();
        assertTrue(true);
    }
}