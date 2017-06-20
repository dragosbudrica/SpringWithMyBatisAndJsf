package ro.kepler.rominfo.filter;

/**
 * Created by Dragos on 17.05.2017.
 */


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ro.kepler.rominfo.beans.LoginView;
import ro.kepler.rominfo.model.User;

import java.io.IOException;
import java.util.*;

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

    private static final String LOGIN_PAGE = "/login.xhtml";
    private static final String UNAUTHORIZED = "/accessDenied.xhtml";

    private static Map<String, ArrayList<String>> authorizations = setAuthorizations();

    private static Map<String, ArrayList<String>> setAuthorizations() {
        Map<String, ArrayList<String>> authorizations = new HashMap<>();
        ArrayList<String> studentPages = new ArrayList<String>();
        studentPages.add("studentCourses");
        studentPages.add("allCourses");
        studentPages.add("timetable");
        studentPages.add("courseDetails");

        ArrayList<String> professorPages = new ArrayList<String>();
        professorPages.add("professorCourses");
        professorPages.add("addNewCourse");
        professorPages.add("timetable");
        professorPages.add("courseDetails");

        authorizations.put("Student", studentPages);
        authorizations.put("Professor", professorPages);

        return authorizations;
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        // managed bean name is exactly the session attribute name
        LoginView loginView = (LoginView) httpServletRequest
                .getSession().getAttribute("loginView");

        if (loginView != null) {
            if (loginView.isLoggedIn()) {
                LOGGER.debug("user is logged in");
                // user is logged in, continue request
                User user = loginView.getUserService().findUser(loginView.getEmail());
                String pageRequested = ((HttpServletRequest) servletRequest).getRequestURL().toString();
                LOGGER.info(user.getRole());
                LOGGER.info(pageRequested);

                if (!isAuthorized(user, pageRequested)) {
                    httpServletResponse.sendRedirect(
                            httpServletRequest.getContextPath()
                                    + UNAUTHORIZED);
                } else {
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            } else {
                LOGGER.debug("user is not logged in");
                // user is not logged in, redirect to login page
                httpServletResponse.sendRedirect(
                        httpServletRequest.getContextPath()
                                + LOGIN_PAGE);
            }
        } else {
            LOGGER.debug("loginView not found");
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

    private boolean isAuthorized(User user, String pageRequested) {
        boolean authorized = false;

        outer:
        for (Map.Entry<String, ArrayList<String>> entry : authorizations.entrySet()) {
            String role = entry.getKey();
            ArrayList<String> pages = entry.getValue();

            for (String page : pages) {
                if (role.equals(user.getRole()) && pageRequested.contains(page)) {
                    authorized = true;
                    break outer;
                }
            }
        }

        return authorized;
    }
}
