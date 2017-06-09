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

    @ManagedProperty("#{loginView}")
    private LoginView loginView;

    private static final String BEGINING_OF_SCHOOL = "2016-10-01";
    private static final String END_OF_SCHOOL = "2017-06-30";

    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    private ScheduleModel eventModel;

    private ScheduleEvent event = new DefaultScheduleEvent();

    private List<CourseDto> getAllCoursesWithDates() {

        List<Course> allCourses =  courseService.getAllCoursesWithDates();
        List<CourseDto> courses = new ArrayList<CourseDto>();
        Calendar cal = Calendar.getInstance(); // creates calendar


        for (Course currentCourse : allCourses) {
            CourseDto courseDto = new CourseDto();
            courseDto.setCourseName(currentCourse.getCourseName());
            courseDto.setStartTime(currentCourse.getStartTime());
            cal.setTime(currentCourse.getStartTime()); // sets start time
            cal.add(Calendar.HOUR_OF_DAY, 2); // adds two hours
            courseDto.setEndTime(cal.getTime());
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
                eventModel.addEvent(newEvent);
            }
        }
    }

    public Date getRandomDate(Date base) {
        Calendar date = Calendar.getInstance();
        date.setTime(base);
        date.add(Calendar.DATE, ((int) (Math.random()*30)) + 1);    //set random day of month

        return date.getTime();
    }

    public Date getInitialDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar.getTime();
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    private Calendar today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar;
    }

    private Date previousDay8Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 8);

        return t.getTime();
    }

    private Date previousDay11Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 11);

        return t.getTime();
    }

    private Date today1Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 1);

        return t.getTime();
    }

    private Date theDayAfter3Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 2);
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 3);

        return t.getTime();
    }

    private Date today6Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 6);

        return t.getTime();
    }

    private Date nextDay9Am() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.HOUR, 9);

        return t.getTime();
    }

    private Date nextDay11Am() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.HOUR, 11);

        return t.getTime();
    }

    private Date fourDaysLater3pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 4);
        t.set(Calendar.HOUR, 3);

        return t.getTime();
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
            List<ScheduleEvent> events = new ArrayList<ScheduleEvent>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.setTime(event.getStartDate());
            try {
                Date beg = sdf.parse(BEGINING_OF_SCHOOL);
                Date end = sdf.parse(END_OF_SCHOOL);
                while(c.getTime().compareTo(beg) >= 0) {
                    Date newStartDate = c.getTime();
                    c.add(Calendar.HOUR_OF_DAY, 2);
                    Date newEndDate = c.getTime();
                    DefaultScheduleEvent backwardRecurrentEvent = new DefaultScheduleEvent(event.getTitle(), newStartDate, newEndDate);
                    events.add(backwardRecurrentEvent);
                    c.setTime(newStartDate);
                    c.add(Calendar.DAY_OF_YEAR, -7);
                }
                c.setTime(event.getStartDate());
                c.add(Calendar.DAY_OF_YEAR, 7);
                while(c.getTime().compareTo(end) <= 0) {
                    Date newStartDate = c.getTime();
                    c.add(Calendar.HOUR_OF_DAY, 2);
                    Date newEndDate = c.getTime();
                    DefaultScheduleEvent forwardRecurrentEvent = new DefaultScheduleEvent(event.getTitle(), newStartDate, newEndDate);
                    events.add(forwardRecurrentEvent);
                    c.setTime(newStartDate);
                    c.add(Calendar.DAY_OF_YEAR, 7);
                }
                for (ScheduleEvent ev: events) {
                    eventModel.addEvent(ev);
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        courseService.setTime(event.getTitle(), event.getStartDate());
        event = new DefaultScheduleEvent();
    }

    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
    }

    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}

