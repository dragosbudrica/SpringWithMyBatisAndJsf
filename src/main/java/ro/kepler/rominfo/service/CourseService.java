package ro.kepler.rominfo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.kepler.rominfo.mappers.CourseMapper;
import ro.kepler.rominfo.model.Course;

import java.util.List;

/**
 * Created by Dragos on 19.05.2017.
 */

@Service
public class CourseService {

    private CourseMapper courseMapper;

    @Autowired
    public CourseService(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    public List<Course> getAllCourses() {
        return courseMapper.getAllCourses();
    }

    public Course getCourseByName(String courseName) {
        return courseMapper.getCourseByName(courseName);
    }

}
