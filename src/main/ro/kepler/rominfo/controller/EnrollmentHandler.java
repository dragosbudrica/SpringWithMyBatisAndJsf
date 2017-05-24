package ro.kepler.rominfo.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;
import ro.kepler.rominfo.dto.CourseDto;
import ro.kepler.rominfo.dto.LoginDto;
import ro.kepler.rominfo.model.Course;
import ro.kepler.rominfo.model.Student;
import ro.kepler.rominfo.service.CourseService;
import ro.kepler.rominfo.service.EnrollmentService;
import ro.kepler.rominfo.service.StudentService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * Created by Dragos on 22.05.2017.
 */

@ManagedBean(name="enrollmentHandler")
@RequestScoped
public class EnrollmentHandler {
    private StudentService studentService;
    private CourseService courseService;

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    private EnrollmentService enrollmentService;

    public void setEnrollmentService(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    private static final Log LOGGER = LogFactory.getLog(EnrollmentHandler.class);


    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public String enroll(LoginDto loginDto, CourseDto courseDto) {
        Student student = studentService.find(loginDto.getEmail());
        Course course = courseService.find(courseDto.getCourseName());

        LOGGER.info(student.getEmail());
        LOGGER.info(course.getCourseName());

        if(student != null) {
            enrollmentService.enroll(student, course);
            LOGGER.info("enrollment successful for " + student.getEmail());
            RequestContext.getCurrentInstance().execute("PF('courseDialog').hide();");
            return "success";
        }
        else {
            LOGGER.info("enrollment failed for " + loginDto.getEmail());
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Enrollment failed.",
                            "The enrollment process has failed."));
            return null;
        }

    }
}
