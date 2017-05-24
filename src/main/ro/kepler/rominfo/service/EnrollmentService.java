package ro.kepler.rominfo.service;

import ro.kepler.rominfo.model.Course;
import ro.kepler.rominfo.model.Student;

/**
 * Created by Dragos on 22.05.2017.
 */
public interface EnrollmentService {
    void enroll(Student student, Course course);
}
