package ro.kepler.rominfo.mapper;

import ro.kepler.rominfo.model.Student;

/**
 * Created by Dragos on 15.05.2017.
 */
public interface StudentMapper {
    void addStudent(Student student);
    Student findByEmail(String email);
}
