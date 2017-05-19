package ro.kepler.rominfo.service;

import ro.kepler.rominfo.dto.LoginDto;
import ro.kepler.rominfo.dto.RegisterDto;
import ro.kepler.rominfo.model.Student;

/**
 * Created by Dragos on 15.05.2017.
 */
public interface StudentService {
    void addStudent(RegisterDto registerDto);
    Student find(String email);
    boolean isValid(LoginDto loginDto);
}
