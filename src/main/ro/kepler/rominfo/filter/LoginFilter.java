package ro.kepler.rominfo.filter;

/**
 * Created by Dragos on 17.05.2017.
 */


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ro.kepler.rominfo.controller.StudentHandler;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginFilter implements Filter {

    private static final Log LOGGER = LogFactory.getLog(LoginFilter.class);

    public static final String LOGIN_PAGE = "/login.xhtml";

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        // managed bean name is exactly the session attribute name
        StudentHandler studentHandler = (StudentHandler) httpServletRequest
                .getSession().getAttribute("studentHandler");

        if (studentHandler != null) {
            if (studentHandler.isLoggedIn()) {
                LOGGER.debug("user is logged in");
                // user is logged in, continue request
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                LOGGER.debug("user is not logged in");
                // user is not logged in, redirect to login page
                httpServletResponse.sendRedirect(
                        httpServletRequest.getContextPath()
                                + LOGIN_PAGE);
            }
        } else {
            LOGGER.debug("studentHandler not found");
            // user is not logged in, redirect to login page
            httpServletResponse.sendRedirect(
                    httpServletRequest.getContextPath() + LOGIN_PAGE);
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        LOGGER.debug("LoginFilter initialized");
    }

    @Override
    public void destroy() {
        // close resources
    }
}
