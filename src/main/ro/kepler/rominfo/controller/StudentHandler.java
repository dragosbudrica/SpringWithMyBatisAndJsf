package ro.kepler.rominfo.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ro.kepler.rominfo.dto.StudentDto;
import ro.kepler.rominfo.filter.LoginFilter;
import ro.kepler.rominfo.model.Student;
import ro.kepler.rominfo.service.StudentService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by Dragos on 16.05.2017.
 */
public class StudentHandler implements Serializable {

    private StudentService studentService;
    private Student currentStudent;

    private static final Log LOGGER = LogFactory.getLog(LoginFilter.class);

    public static final String HOME_PAGE_REDIRECT = "/secured/home.xhtml?faces-redirect=true";
    public static final String LOGOUT_PAGE_REDIRECT = "/logout.xhtml?faces-redirect=true";

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }


    public String login(StudentDto studentDto) {
        currentStudent = studentService.find(studentDto);

        if (currentStudent != null) {
            LOGGER.info("login successful for " + studentDto.getEmail());
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            Map<String, Object> sessionMap = externalContext.getSessionMap();
            sessionMap.put("studentHandler", this);
            return HOME_PAGE_REDIRECT;
        } else {
            LOGGER.info("login failed for " + studentDto.getEmail());
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Login failed",
                            "Invalid or unknown credentials."));

            return null;
        }
    }

    public String logout() {
        String identifier = currentStudent.getEmail();

        // invalidate the session
        LOGGER.debug("invalidating session for " + identifier);
        FacesContext.getCurrentInstance().getExternalContext()
                .invalidateSession();

        LOGGER.info("logout successful for " + identifier);
        return LOGOUT_PAGE_REDIRECT;
    }

    public boolean isLoggedIn() {
        return currentStudent != null;
    }

    public String loggedInForwardHome() {
        if (isLoggedIn()) {
            return HOME_PAGE_REDIRECT;
        }
        return null;
    }

    public void register(StudentDto studentDto) {
        // do something with studentDto
        studentService.addStudent(studentDto);
    }

    public Student getCurrentStudent() {
        return currentStudent;
    }
}
