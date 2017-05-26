package ro.kepler.rominfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.kepler.rominfo.mappers.StudentMapper;
import ro.kepler.rominfo.model.Student;


/**
 * Created by Dragos on 15.05.2017.
 */

@Service
public class StudentService {
    private StudentMapper studentMapper;

    @Autowired
    public StudentService(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Transactional
    public void addStudent(String firstName, String lastName, long ssn, String email, String password) {
       Student student = new Student();
       student.setFirstName(firstName);
       student.setLastName(lastName);
       student.setSsn(ssn);
       student.setEmail(email);
       student.setPassword(password);
        studentMapper.addStudent(student);
    }

    public boolean find(String email) {
        Student student =  studentMapper.findByEmail(email);
        return student != null;
    }

    public Student getStudentByEmail(String email) {
        return studentMapper.findByEmail(email);
    }

    public boolean isValid(String email) {
        Student student = studentMapper.findByEmail(email);
        return (student.getEmail().equals(email));
    }
}
