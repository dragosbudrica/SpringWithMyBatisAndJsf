package ro.kepler.rominfo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.kepler.rominfo.dto.LoginDto;
import ro.kepler.rominfo.dto.RegisterDto;
import ro.kepler.rominfo.mapper.StudentMapper;
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
    public void addStudent(RegisterDto registerDto) {
       Student student = new Student();
       student.setFirstName(registerDto.getFirstName());
       student.setLastName(registerDto.getLastName());
       student.setSsn(registerDto.getSsn());
       student.setEmail(registerDto.getEmail());
       student.setPassword(registerDto.getPassword());
        studentMapper.addStudent(student);
    }

    public Student find(String email) {
        return studentMapper.findByEmail(email);
    }

    public boolean isValid(LoginDto loginDto) {
        Student student = studentMapper.findByEmail(loginDto.getEmail());
        if(student.getEmail().equals(loginDto.getEmail()))
            return true;
        else
            return false;
    }
}
