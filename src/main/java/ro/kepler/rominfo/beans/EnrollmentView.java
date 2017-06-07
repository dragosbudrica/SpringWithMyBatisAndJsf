package ro.kepler.rominfo.beans;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;
import ro.kepler.rominfo.dto.CourseDto;
import ro.kepler.rominfo.model.Course;
import ro.kepler.rominfo.model.User;
import ro.kepler.rominfo.service.CourseService;
import ro.kepler.rominfo.service.EnrollmentService;
import ro.kepler.rominfo.service.UserService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 * Created by Dragos on 22.05.2017.
 */

@ManagedBean
@ViewScoped
public class EnrollmentView implements Serializable {

    @ManagedProperty("#{userService}")
    private UserService userService;

    @ManagedProperty("#{courseService}")
    private CourseService courseService;

    @ManagedProperty("#{enrollmentService}")
    private EnrollmentService enrollmentService;

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    public void setEnrollmentService(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    private static final Log LOGGER = LogFactory.getLog(EnrollmentView.class);


    public void setUserService(UserService userService) {
        this.userService = userService;
    }

/*    public String enroll(String email, CourseDto courseDto) {
        User student = userService.find(email);
        Course course = courseService.getCourseByName(courseDto.getCourseName());

        try {
            if(!enrollmentService.alreadyEnrolled(student, course)) {
                enrollmentService.enroll(student, course);
                LOGGER.info("enrollment successful for " + student.getEmail());
                RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO, "Enrollment Successful", "Now you have enrolled to " + courseDto.getCourseName() + " course!"));
                RequestContext.getCurrentInstance().execute("PF('courseDialog').hide();");
                return "success";
            }
            else {
                LOGGER.info("student already enrolled for this course");
                RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Enrollment Failed", "You are already enrolled at that course!"));
                RequestContext.getCurrentInstance().execute("PF('courseDialog').hide();");
                return "failed";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.info("enrollment failed for " + email);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Enrollment failed.",
                            "The enrollment process has failed."));
            return null;
        }
    }*/
}
