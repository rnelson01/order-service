package inside;

import org.junit.Test;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.Assert.*;

public class InsideFilterTest {

    InsideFilter filter = new InsideFilter();
    @Test
    public void destroy() {
        filter.destroy();
        assertTrue(true);
    }

    @Test
    public void doFilter() throws IOException, ServletException {
        filter.doFilter(null, null, null);
        assertTrue(true);
    }

    @Test
    public void init() throws ServletException {
        filter.init(null);
        assertTrue(true);
    }
}