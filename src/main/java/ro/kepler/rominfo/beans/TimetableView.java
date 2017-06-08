package ro.kepler.rominfo.beans;

/**
 * Created by Dragos on 26.05.2017.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;
import ro.kepler.rominfo.dto.CourseDto;
import ro.kepler.rominfo.model.Course;
import ro.kepler.rominfo.service.CourseService;
import ro.kepler.rominfo.service.UserService;

@ManagedBean
@ViewScoped
public class TimetableView implements Serializable {

    @ManagedProperty("#{courseService}")
    private CourseService courseService;

    @ManagedProperty("#{userService}")
    private UserService userService;

    @ManagedProperty("#{loginView}")
    private LoginView loginView;

    private boolean rendered = true;

    public boolean isRendered() {
        return rendered;
    }

    public void setRendered(boolean rendered) {
        this.rendered = rendered;
    }

    public boolean isStudent() {
        String role = userService.findUser(loginView.getEmail()).getRole();
        return role.equals("Student");
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    private ScheduleModel eventModel;

    private List<CourseDto> getAllCoursesWithDates() {

        List<Course> myCourses = courseService.getAllCoursesWithDates();
        List<CourseDto> courses = new ArrayList<CourseDto>();

        for (Course currentCourse : myCourses) {
            CourseDto courseDto = new CourseDto();
            courseDto.setCourseName(currentCourse.getCourseName());
            courseDto.setStartTime(currentCourse.getStartTime());
            courseDto.setEndTime(currentCourse.getEndTime());
            courses.add(courseDto);
        }
        return courses;
    }

    @PostConstruct
    public void init() {
        eventModel = new DefaultScheduleModel();
        boolean timetableUnderConstruction = false;
        for (CourseDto course : getAllCoursesWithDates()) {
            if (course.getStartTime() != null && course.getEndTime() != null) {
                eventModel.addEvent(new DefaultScheduleEvent(course.getCourseName(), course.getStartTime(), course.getEndTime()));
            }
            else {
                timetableUnderConstruction = true;
                break;
            }

        }
        if(timetableUnderConstruction) {
            rendered = false;
        }
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }
}
