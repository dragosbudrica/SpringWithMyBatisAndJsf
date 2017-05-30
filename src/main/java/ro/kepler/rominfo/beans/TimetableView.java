package ro.kepler.rominfo.beans;

/**
 * Created by Dragos on 26.05.2017.
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;
import ro.kepler.rominfo.dto.CourseDto;
import ro.kepler.rominfo.model.Course;
import ro.kepler.rominfo.service.CourseService;

@ManagedBean
@ViewScoped
public class TimetableView implements Serializable {

    @ManagedProperty("#{courseService}")
    private CourseService courseService;

    @ManagedProperty("#{loginView}")
    private LoginView loginView;

    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    private ScheduleModel eventModel;

    private List<CourseDto> getMyCoursesWithDates(String email) {

        List<Course> myCourses =  courseService.getStudentCoursesWithDates(email);
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
        for (CourseDto course: getMyCoursesWithDates(loginView.getEmail())) {
                eventModel.addEvent(new DefaultScheduleEvent(course.getCourseName(), course.getStartTime(), course.getEndTime()));
        }
    }
    public ScheduleModel getEventModel() {
        return eventModel;
    }
}
