package ro.kepler.rominfo.beans;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ro.kepler.rominfo.model.User;
import ro.kepler.rominfo.service.StudentService;
import ro.kepler.rominfo.service.UserService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 * Created by Dragos on 19.05.2017.
 */

@ManagedBean
@ViewScoped
public class RegisterView implements Serializable {

    @ManagedProperty("#{userService}")
    private UserService userService;

    private String firstName;
    private String lastName;
    private long ssn;
    private String email;
    private String password;
    private String role;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getSsn() {
        return ssn;
    }

    public void setSsn(long ssn) {
        this.ssn = ssn;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private static final Log LOGGER = LogFactory.getLog(RegisterView.class);

    public static final String LOGIN_PAGE_REDIRECT = "login.xhtml?faces-redirect=true";

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String register() {
        User user = userService.find(email);
        if(user != null) {
            LOGGER.info("register successful for " + email);
            userService.addUser(firstName, lastName, ssn, email, password, role);
            return LOGIN_PAGE_REDIRECT;
        }
        else {
            LOGGER.info("register failed for " + email);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Register failed. That email is already used.",
                            "That email is already used."));
            return null;
        }
    }
}
