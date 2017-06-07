package ro.kepler.rominfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.kepler.rominfo.mappers.UserMapper;
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
        userMapper.addUser(user);
        if(role.equals("Student")) {
            User student = userMapper.findByEmail(email);
            userMapper.addStudent(student.getUserId());
        } else {
            User professor = userMapper.findByEmail(email);
            userMapper.addProfessor(professor.getUserId());
        }
    }

    public User find(String email) {
        return userMapper.findByEmail(email);
    }
}
