package ro.kepler.rominfo.beans;

/**
 * Created by Dragos on 30.05.2017.
 */
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import ro.kepler.rominfo.dto.CourseDto;
import ro.kepler.rominfo.model.Course;
import ro.kepler.rominfo.service.CourseService;

@ManagedBean
@ViewScoped
public class CourseSchedulingView implements Serializable {

    @ManagedProperty("#{courseService}")
    private CourseService courseService;

    private static final String BEGINNING_OF_SCHOOL = "2016-10-01";
    private static final String END_OF_SCHOOL = "2017-07-01";

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    private ScheduleModel eventModel;

    private ScheduleEvent event = new DefaultScheduleEvent();

    List<CourseDto> getAllCoursesWithDates() {

        List<Course> allCourses =  courseService.getAllCoursesWithDates();
        List<CourseDto> courses = new ArrayList<CourseDto>();
        Calendar cal = Calendar.getInstance(); // creates calendar


        for (Course currentCourse : allCourses) {
            CourseDto courseDto = new CourseDto();
            courseDto.setCourseName(currentCourse.getCourseName());
            if(currentCourse.getStartTime() != null) {
                courseDto.setStartTime(currentCourse.getStartTime());
                cal.setTime(currentCourse.getStartTime()); // sets start time
                cal.add(Calendar.HOUR_OF_DAY, 2); // adds two hours
                courseDto.setEndTime(cal.getTime());
            }
            courses.add(courseDto);
        }
        return courses;
    }

    public List<String> getAllCourseTitles() {

        List<Course> myCourses =  courseService.getAllCourses();
        List<String> courses = new ArrayList<String>();

        for (Course currentCourse : myCourses) {
            String courseName = currentCourse.getCourseName();
            courses.add(courseName);
        }
        return courses;
    }

    @PostConstruct
    public void init() {
        eventModel = new DefaultScheduleModel();
        for (CourseDto course: getAllCoursesWithDates()) {
            if (course.getStartTime() != null) {
                DefaultScheduleEvent newEvent = new DefaultScheduleEvent(course.getCourseName(), course.getStartTime(), course.getEndTime());
                newEvent.setEditable(false);
                for (ScheduleEvent ev: getAllRecurrentEvents(newEvent)) {
                    eventModel.addEvent(ev);
                }
            }
        }
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public void addEvent(ActionEvent actionEvent) {
        ScheduleEvent se = null;
        for (ScheduleEvent sev: eventModel.getEvents()) {
            if (sev.getTitle().equals(event.getTitle())) {
                se = sev;
                break;
            }
        }
        if(se != null) {
            event.setId(se.getId());
            eventModel.updateEvent(event);
        }
        else {
            List<ScheduleEvent> events = getAllRecurrentEvents(event);
            for (ScheduleEvent ev: events) {
                eventModel.addEvent(ev);
            }
        }
        courseService.setTime(event.getTitle(), event.getStartDate());
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Course scheduled", "Course scheduled");
        addMessage(message);
        event = new DefaultScheduleEvent();
    }

    List<ScheduleEvent> getAllRecurrentEvents(ScheduleEvent event) {
        List<ScheduleEvent> events = new ArrayList<ScheduleEvent>();
        DefaultScheduleEvent recurringEvent;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(event.getStartDate());
        Date newStartTime;
        Date newEndTime;

        try {
            Date beg = sdf.parse(BEGINNING_OF_SCHOOL);
            Date end = sdf.parse(END_OF_SCHOOL);
            while(c.getTime().compareTo(beg) > 0) {
                newStartTime = c.getTime();
                newEndTime = getEndTime(c, newStartTime, 2);
                recurringEvent = new DefaultScheduleEvent(event.getTitle(), newStartTime, newEndTime);
                events.add(recurringEvent);
                setupNewRecurringEvent(c, newStartTime, -7);
            }
            setupNewRecurringEvent(c, event.getStartDate(), 7);
            while(c.getTime().compareTo(end) < 0) {
                newStartTime = c.getTime();
                newEndTime = getEndTime(c, newStartTime, 2);
                recurringEvent = new DefaultScheduleEvent(event.getTitle(), newStartTime, newEndTime);
                events.add(recurringEvent);
                setupNewRecurringEvent(c, newStartTime, 7);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return events;
    }

    private Date getEndTime(Calendar c, Date newStartTime, int amount) {
        c.setTime(newStartTime);
        c.add(Calendar.HOUR_OF_DAY, amount);
        return c.getTime();
    }

    private void setupNewRecurringEvent(Calendar c, Date newStartTime, int amount) {
        c.setTime(newStartTime);
        c.add(Calendar.DAY_OF_YEAR, amount);
    }

    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
    }

    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}

