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
 * Created by Dragos on 29.05.2017.
 */

@ManagedBean
@ViewScoped
public class ProfessorCourseView implements Serializable {
    @ManagedProperty("#{courseService}")
    private CourseService courseService;

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    public List<CourseDto> getProfessorCourses(String email) {

        List<Course> myCourses =  courseService.getProfessorCourses(email);
        List<CourseDto> courses = new ArrayList<CourseDto>();

        for (Course currentCourse : myCourses) {
            CourseDto courseDto = new CourseDto();
            courseDto.setCourseName(currentCourse.getCourseName());
            courseDto.setCategory(currentCourse.getCategory());
            courses.add(courseDto);
        }
        return courses;
    }

    public List<String> getProfessorCourseTitles(String email) {

        List<Course> myCourses =  courseService.getProfessorCourses(email);
        List<String> courses = new ArrayList<String>();

        for (Course currentCourse : myCourses) {
            String courseName = new String(currentCourse.getCourseName());
            courses.add(courseName);
        }
        return courses;
    }
}
