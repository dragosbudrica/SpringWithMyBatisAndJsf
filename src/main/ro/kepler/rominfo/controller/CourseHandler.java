package ro.kepler.rominfo.controller;

import ro.kepler.rominfo.dto.CourseDto;
import ro.kepler.rominfo.model.Course;
import ro.kepler.rominfo.model.Professor;
import ro.kepler.rominfo.service.CourseService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dragos on 19.05.2017.
 */
public class CourseHandler {

    private CourseService courseService;

    private Professor professor;

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
}
