package ro.kepler.rominfo.beans;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import ro.kepler.rominfo.dto.CourseDto;
import ro.kepler.rominfo.dto.LectureDto;
import ro.kepler.rominfo.model.Course;
import ro.kepler.rominfo.model.Lecture;
import ro.kepler.rominfo.service.CourseService;
import ro.kepler.rominfo.service.LectureService;
import ro.kepler.rominfo.service.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.*;

/**
 * Created by Dragos on 12.06.2017.
 */

@ManagedBean
@ViewScoped
public class LectureView implements Serializable {

    @ManagedProperty("#{courseService}")
    private CourseService courseService;

    @ManagedProperty("#{lectureService}")
    private LectureService lectureService;

    @ManagedProperty("#{userService}")
    private UserService userService;

    @ManagedProperty("#{loginView}")
    private LoginView loginView;

    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private String courseName;

    public boolean isProfessor() {
        String role = userService.findUser(loginView.getEmail()).getRole();
        return role.equals("Professor");
    }

    public StreamedContent downloadPDF(String lectureName) {
        Course course = courseService.getCourseByName(courseName);
        Lecture lecture = lectureService.getLectureByNameAndCourseCode(course.getCourseCode(), lectureName);
        InputStream stream = new ByteArrayInputStream(lecture.getAttachment());
        return new DefaultStreamedContent(stream, "application/pdf", lecture.getName().concat(".pdf"));
    }

    public boolean isMyCourse() {
        List<Course> courses = courseService.getProfessorCourses(loginView.getEmail());
        boolean isMyCourse = false;
        for (Course course: courses) {
            if(course.getCourseName().equals(courseName)) {
                isMyCourse = true;
                break;
            }
        }
        return isMyCourse;
    }

    public LectureService getLectureService() {
        return lectureService;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    public void setLectureService(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    public List<LectureDto> getLectures() {

        Course course = courseService.getCourseByName(courseName);
        List<Lecture> lectureList = lectureService.getLectures(course.getCourseCode());
        List<LectureDto> lectures = new ArrayList<>();
        CourseDto courseDto = new CourseDto();
        courseDto.setCourseName(course.getCourseName());
        for (Lecture l : lectureList) {
            LectureDto lectureDto = new LectureDto();
            lectureDto.setLectureName(l.getName());
            lectureDto.setFile(l.getAttachment());
            lectures.add(lectureDto);
        }
        return lectures;
    }

    public void upload(FileUploadEvent event) throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        LectureDto lectureDto = context.getApplication().evaluateExpressionGet(context, "#{lectureDto}", LectureDto.class);
        Course course = courseService.getCourseByName(courseName);
        Lecture lecture = lectureService.getLectureByNameAndCourseCode(course.getCourseCode(), lectureDto.getLectureName());
        lectureService.uploadFile(event.getFile(), lecture.getLectureId());
    }
}
