package ro.kepler.rominfo.beans;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ro.kepler.rominfo.filter.LoginFilter;
import ro.kepler.rominfo.model.Student;
import ro.kepler.rominfo.service.StudentService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
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

    @ManagedProperty("#{studentService}")
    private StudentService studentService;

    private String email;
    private String password;

    private static final Log LOGGER = LogFactory.getLog(LoginFilter.class);

    public static final String HOME_PAGE_REDIRECT = "/secured/allCourses.xhtml?faces-redirect=true";
    public static final String LOGOUT_PAGE_REDIRECT = "/logout.xhtml?faces-redirect=true";

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Method that checks login
     * @return
     */
    public String login() {
        if (studentService.find(email)) {
            LOGGER.info("login successful for " + email);
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            Map<String, Object> sessionMap = externalContext.getSessionMap();
            sessionMap.put("loginView", this);
            return HOME_PAGE_REDIRECT;
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

        email = null;
        password = null;

        LOGGER.info("logout successful for " + email);
        return LOGOUT_PAGE_REDIRECT;
    }

    public boolean isLoggedIn() {
        return email != null;
    }

    public String loggedInForwardHome() {
        if (isLoggedIn()) {
            return HOME_PAGE_REDIRECT;
        }
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
