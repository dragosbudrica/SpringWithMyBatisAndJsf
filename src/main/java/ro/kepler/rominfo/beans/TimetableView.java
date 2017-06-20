package ro.kepler.rominfo.beans;

/**
 * Created by Dragos on 26.05.2017.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import ro.kepler.rominfo.dto.CourseDto;
import ro.kepler.rominfo.model.Course;
import ro.kepler.rominfo.service.CourseService;
import ro.kepler.rominfo.service.UserService;

@ManagedBean
@ViewScoped
public class TimetableView implements Serializable {

    @ManagedProperty("#{userService}")
    private UserService userService;

    @ManagedProperty("#{loginView}")
    private LoginView loginView;

    @ManagedProperty("#{courseSchedulingView}")
    private CourseSchedulingView courseSchedulingView;

    public void setCourseSchedulingView(CourseSchedulingView courseSchedulingView) {
        this.courseSchedulingView = courseSchedulingView;
    }

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


    private ScheduleModel eventModel;


    @PostConstruct
    public void init() {
        eventModel = new DefaultScheduleModel();
        boolean timetableUnderConstruction = false;
        for (CourseDto course : courseSchedulingView.getAllCoursesWithDates()) {
            if (course.getStartTime() == null) {
                timetableUnderConstruction = true;
            }
        }

        if (timetableUnderConstruction) {
            rendered = false;
        } else {
            for (CourseDto course : courseSchedulingView.getAllCoursesWithDates()) {
                DefaultScheduleEvent newEvent = new DefaultScheduleEvent(course.getCourseName(), course.getStartTime(), course.getEndTime());
                newEvent.setEditable(false);
                for (ScheduleEvent ev : courseSchedulingView.getAllRecurrentEvents(newEvent)) {
                    eventModel.addEvent(ev);
                }
            }
            rendered = true;
        }
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }
}
