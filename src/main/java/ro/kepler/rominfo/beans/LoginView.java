package ro.kepler.rominfo.beans;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ro.kepler.rominfo.filter.LoginFilter;
import ro.kepler.rominfo.model.User;
import ro.kepler.rominfo.service.UserService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by Dragos on 16.05.2017.
 */

@ManagedBean
@SessionScoped
public class LoginView implements Serializable {

    @ManagedProperty("#{userService}")
    private UserService userService;

    private String email;
    private String password;

    private static final Log LOGGER = LogFactory.getLog(LoginFilter.class);

    private static final String ALL_COURSES_REDIRECT = "/secured/allCourses.xhtml?faces-redirect=true";
    private static final String PROFESSOR_COURSES_REDIRECT = "/secured/professorCourses.xhtml?faces-redirect=true";
    private static final String LOGOUT_PAGE_REDIRECT = "/logout.xhtml?faces-redirect=true";
    private static final String REGISTER_REDIRECT = "/secured/register.xhtml?faces-redirect=true";

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }

    /**
     * Method that checks login
     * @return
     */
    public String login() {
        User user = userService.find(email);
        if (user != null) {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            Map<String, Object> sessionMap = externalContext.getSessionMap();
            sessionMap.put("loginView", this);
            if(user.getEmail().equals("admin")) {
                return REGISTER_REDIRECT;
            }
            else if(user.getRole().equals("Professor")) {
                return PROFESSOR_COURSES_REDIRECT;
            }
                else {
                    return ALL_COURSES_REDIRECT;
            }
        } else {
            LOGGER.info("login failed for " + email);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Login failed",
                            "Invalid or unknown credentials."));
            return null;
        }
    }

    public String logout() {
        // invalidate the session
        LOGGER.debug("invalidating session for " + email);
        FacesContext.getCurrentInstance().getExternalContext()
                .invalidateSession();
        LOGGER.info("register successful for " + email);

        email = null;
        password = null;

        return LOGOUT_PAGE_REDIRECT;
    }

    public boolean isLoggedIn() {
        return email != null;
    }

    public String loggedInForward() {
        if (isLoggedIn() && FacesContext.getCurrentInstance().getExternalContext().isUserInRole("student")) {
            return ALL_COURSES_REDIRECT;
        }
        else  if (isLoggedIn() && FacesContext.getCurrentInstance().getExternalContext().isUserInRole("professor")) {
            return PROFESSOR_COURSES_REDIRECT;
        }
        else
        return null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
