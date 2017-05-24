package ro.kepler.rominfo.service.impl;

import org.springframework.transaction.annotation.Transactional;
import ro.kepler.rominfo.mapper.EnrollmentMapper;
import ro.kepler.rominfo.model.Course;
import ro.kepler.rominfo.model.Student;
import ro.kepler.rominfo.service.EnrollmentService;

/**
 * Created by Dragos on 22.05.2017.
 */
public class EnrollmentServiceImpl implements EnrollmentService {
    private EnrollmentMapper enrollmentMapper;

    public void setEnrollmentMapper(EnrollmentMapper enrollmentMapper) {
        this.enrollmentMapper = enrollmentMapper;
    }

    @Transactional
    public void enroll(Student student, Course course) {
        student.getCourses().add(course);
        course.getStudents().add(student);
        enrollmentMapper.addEnrollment(student.getStudentId(), course.getCourseCode());
    }
}
