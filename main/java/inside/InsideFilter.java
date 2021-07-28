package inside;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Servlet Filter implementation class InsideFilter
 */
public class InsideFilter implements Filter {

  private final static Logger logger = Logger.getLogger(InsideFilter.class);

  /**
   * Default constructor.
   */
  public InsideFilter() {
  }

  /**
   * @see Filter#destroy()
   */
  public void destroy() {
  }

  /**
   * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
   */
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    if( request == null) {
      logger.info("Received null request");
      return;
    }
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;
    HttpSession session = req.getSession(false);
    boolean flag = true;
//    if (session == null)
//      flag = false;
//    else {
//      if (session.getAttribute("name") == null)
//        flag = false;
//    }
    if (flag) {
      chain.doFilter(req, res);
    } else {
      res.sendRedirect("/todolist/login");
    }
  }

  /**
   * @see Filter#init(FilterConfig)
   */
  public void init(FilterConfig fConfig) throws ServletException {
  }

}
