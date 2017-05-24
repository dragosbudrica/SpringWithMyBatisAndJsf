package ro.kepler.rominfo.service.impl;

import ro.kepler.rominfo.mapper.CourseMapper;
import ro.kepler.rominfo.model.Course;
import ro.kepler.rominfo.service.CourseService;

import java.util.List;

/**
 * Created by Dragos on 19.05.2017.
 */
public class CourseServiceImpl implements CourseService {

    private CourseMapper courseMapper;

    public void setCourseMapper(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    public List<Course> getAllCourses() {
        return courseMapper.getAllCourses();
    }

    public Course find(String courseName) {
        return courseMapper.getCourseByName(courseName);
    }

}
