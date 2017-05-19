package ro.kepler.rominfo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.kepler.rominfo.mapper.StudentMapper;
import ro.kepler.rominfo.dto.StudentDto;
import ro.kepler.rominfo.model.Student;
import ro.kepler.rominfo.service.StudentService;

/**
 * Created by Dragos on 15.05.2017.
 */

@Service
public class StudentServiceImpl implements StudentService {
    private StudentMapper studentMapper;

    public void setStudentMapper(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Transactional
    public void addStudent(StudentDto studentDto) {
       Student student = new Student();
       student.setFirstName(studentDto.getFirstName());
       student.setLastName(studentDto.getLastName());
       student.setSsn(studentDto.getSsn());
       student.setEmail(studentDto.getEmail());
       student.setPassword(studentDto.getPassword());
        studentMapper.addStudent(student);
    }

    public Student find(StudentDto studentDto) {
        return studentMapper.findByEmail(studentDto.getEmail());
    }

    public boolean isValid(StudentDto studentDto) {
        Student student = studentMapper.findByEmail(studentDto.getEmail());
        if(student.getEmail().equals(studentDto.getEmail()))
            return true;
        else
            return false;
    }




}
