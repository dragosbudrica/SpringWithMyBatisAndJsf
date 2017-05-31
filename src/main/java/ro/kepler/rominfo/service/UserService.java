package ro.kepler.rominfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.kepler.rominfo.mappers.StudentMapper;
import ro.kepler.rominfo.mappers.UserMapper;
import ro.kepler.rominfo.model.Student;
import ro.kepler.rominfo.model.User;

/**
 * Created by Dragos on 31.05.2017.
 */

@Service
public class UserService {

    private UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Transactional
    public void addUser(String firstName, String lastName, long ssn, String email, String password, String role) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setSsn(ssn);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        if (role.equals("Professor"))
            userMapper.addProfessorUser(user);
        else
            userMapper.addStudentUser(user);
    }

    public User find(String email) {
        return userMapper.findByEmail(email);
    }
}
