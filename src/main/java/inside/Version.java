package inside;

import org.apache.commons.lang3.StringUtils;
        import org.apache.log4j.Logger;

        import java.io.IOException;
        import javax.servlet.ServletException;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */

public class Version extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(Version.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if( request == null) {
            logger.info("Received null request.");
            return;
        }
        String version = "1.0";
        if (!StringUtils.isEmpty(System.getenv("version"))) {
            version = System.getenv("version");
        }
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/text");
        response.getOutputStream().println(version);
    }
}
