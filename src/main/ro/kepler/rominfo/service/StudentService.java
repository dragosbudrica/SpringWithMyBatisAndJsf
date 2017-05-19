package ro.kepler.rominfo.service;

import ro.kepler.rominfo.dto.StudentDto;
import ro.kepler.rominfo.model.Student;

/**
 * Created by Dragos on 15.05.2017.
 */
public interface StudentService {
    void addStudent(StudentDto studentDto);
    Student find(StudentDto studentDto);
    boolean isValid(StudentDto studentDto);
}
