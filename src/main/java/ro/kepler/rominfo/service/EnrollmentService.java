package ro.kepler.rominfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.kepler.rominfo.mappers.EnrollmentMapper;
import ro.kepler.rominfo.model.Course;
import ro.kepler.rominfo.model.Student;
import ro.kepler.rominfo.model.User;

import java.util.List;

/**
 * Created by Dragos on 22.05.2017.
 */

@Service
public class EnrollmentService {
    private EnrollmentMapper enrollmentMapper;

    @Autowired
    public EnrollmentService(EnrollmentMapper enrollmentMapper) {
        this.enrollmentMapper = enrollmentMapper;
    }

    @Transactional
    public void enroll(User student, Course course) {
        ((Student)student).getCourses().add(course);
        course.getStudents().add((Student)student);
        enrollmentMapper.addEnrollment(student.getUserId(), course.getCourseCode());
    }

    public boolean alreadyEnrolled(User student, Course course) {
        for (Course currentCourse : enrollmentMapper.getCoursesOfStudent(student.getUserId())) {
            if(currentCourse.getCourseCode() == course.getCourseCode())
                return true;
        }
        return false;
    }
}
