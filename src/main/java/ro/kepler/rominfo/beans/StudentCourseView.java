package ro.kepler.rominfo.beans;


import ro.kepler.rominfo.dto.CourseDto;
import ro.kepler.rominfo.model.Course;
import ro.kepler.rominfo.model.Professor;
import ro.kepler.rominfo.service.CourseService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dragos on 19.05.2017.
 */
@ManagedBean
@ViewScoped
public class StudentCourseView implements Serializable {

    @ManagedProperty("#{courseService}")
    private CourseService courseService;

    private Professor professor;
    private CourseDto selectedCourse;

    public void setSelectedCourse(CourseDto selectedCourse) {
        this.selectedCourse = selectedCourse;
    }

    public CourseDto getSelectedCourse() {
        return selectedCourse;
    }

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    public List<CourseDto> getAllCourses() {

        List<Course> allCourses =  courseService.getAllCourses();
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

 /*   public List<CourseDto> getStudentCourses(String email) {

        List<Course> myCourses =  courseService.getStudentCourses(email);
        List<CourseDto> courses = new ArrayList<CourseDto>();

        for (Course currentCourse : myCourses) {
            CourseDto courseDto = new CourseDto();
            courseDto.setCourseName(currentCourse.getCourseName());
            courseDto.setCategory(currentCourse.getCategory());
            courseDto.setProfessor(currentCourse.getProfessor().getFullName());
            courses.add(courseDto);
        }
        return courses;
    } */
}
