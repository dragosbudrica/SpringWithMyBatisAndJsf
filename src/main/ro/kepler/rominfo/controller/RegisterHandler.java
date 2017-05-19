package ro.kepler.rominfo.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ro.kepler.rominfo.dto.RegisterDto;
import ro.kepler.rominfo.filter.LoginFilter;
import ro.kepler.rominfo.model.Student;
import ro.kepler.rominfo.service.StudentService;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 * Created by Dragos on 19.05.2017.
 */
public class RegisterHandler implements Serializable {
    private StudentService studentService;
    private Student currentStudent;

    private static final Log LOGGER = LogFactory.getLog(RegisterHandler.class);

    public static final String LOGIN_PAGE_REDIRECT = "login.xhtml?faces-redirect=true";

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public String register(RegisterDto registerDto) {
        currentStudent = studentService.find(registerDto.getEmail());

        if(currentStudent == null) {
            LOGGER.info("register successful for " + registerDto.getEmail());
            studentService.addStudent(registerDto);
            return LOGIN_PAGE_REDIRECT;
        }
        else {
            LOGGER.info("register failed for " + registerDto.getEmail());
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Register failed. That email is already used.",
                            "That email is already used."));
            return null;
        }
    }
}
