package ro.kepler.rominfo.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.kepler.rominfo.dto.CourseDto;
import ro.kepler.rominfo.model.Course;
import ro.kepler.rominfo.model.Professor;
import ro.kepler.rominfo.service.CourseService;
import sun.rmi.runtime.Log;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dragos on 06.06.2017.
 */

@ManagedBean
@ViewScoped
public class CourseView implements Serializable {

    @ManagedProperty("#{courseService}")
    private CourseService courseService;

    @ManagedProperty("#{loginView}")
    private LoginView loginView;

    private CourseDto selectedCourse;
    private String courseName;
    private String category;

    public void setSelectedCourse(CourseDto selectedCourse) {
        this.selectedCourse = selectedCourse;
    }

    public CourseDto getSelectedCourse() {
        return selectedCourse;
    }


    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public void addCourse() {
        courseService.addCourse(courseName, category, loginView.getEmail());
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Course added successfully.",
                        "That account has been created."));
    }

    public List<CourseDto> getProfessorCourses(String email) {

        List<Course> myCourses = courseService.getProfessorCourses(email);
        List<CourseDto> courses = new ArrayList<CourseDto>();

        for (Course currentCourse : myCourses) {
            CourseDto courseDto = new CourseDto();
            courseDto.setCourseName(currentCourse.getCourseName());
            courseDto.setCategory(currentCourse.getCategory());
            courses.add(courseDto);
        }
        return courses;
    }
/*
    public List<CourseDto> getStudentCourses(String email) {

        List<Course> myCourses = courseService.getStudentCourses(email);
        List<CourseDto> courses = new ArrayList<CourseDto>();

        for (Course currentCourse : myCourses) {
            CourseDto courseDto = new CourseDto();
            courseDto.setCourseName(currentCourse.getCourseName());
            courseDto.setCategory(currentCourse.getCategory());
            courseDto.setProfessor(currentCourse.getProfessor().getFullName());
            courses.add(courseDto);
        }
        return courses;
    }
 */
    public List<CourseDto> getAllCourses() {

        List<Course> allCourses = courseService.getAllCourses();
        List<CourseDto> courses = new ArrayList<CourseDto>();

        for (Course currentCourse : allCourses) {
            CourseDto courseDto = new CourseDto();
            courseDto.setCourseName(currentCourse.getCourseName());
            courseDto.setCategory(currentCourse.getCategory());
            courseDto.setProfessor(currentCourse.getProfessor().getFullName());
            courses.add(courseDto);
        }
        return courses;
    }

}
