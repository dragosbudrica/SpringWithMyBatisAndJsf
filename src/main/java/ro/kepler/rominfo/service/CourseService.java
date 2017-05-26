package ro.kepler.rominfo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.kepler.rominfo.mappers.CourseMapper;
import ro.kepler.rominfo.mappers.StudentMapper;
import ro.kepler.rominfo.model.Course;
import ro.kepler.rominfo.model.Student;

import java.util.List;

/**
 * Created by Dragos on 19.05.2017.
 */

@Service
public class CourseService {

    private CourseMapper courseMapper;
    private StudentMapper studentMapper;

    @Autowired
    public CourseService(CourseMapper courseMapper, StudentMapper studentMapper) {
        this.courseMapper = courseMapper;
        this.studentMapper = studentMapper;
    }

    public List<Course> getAllCourses() {
        return courseMapper.getAllCourses();
    }

    public List<Course> getMyCourses(String email) {
        Student student = studentMapper.findByEmail(email);
        return courseMapper.getMyCourses(student.getStudentId());
    }

    public Course getCourseByName(String courseName) {
        return courseMapper.getCourseByName(courseName);
    }

}
