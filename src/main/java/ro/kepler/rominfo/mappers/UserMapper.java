package ro.kepler.rominfo.mappers;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ro.kepler.rominfo.model.Professor;
import ro.kepler.rominfo.model.Student;
import ro.kepler.rominfo.model.User;

import java.util.List;

/**
 * Created by Dragos on 31.05.2017.
 */

@Repository
public interface UserMapper {

    List<User> getAllUsers();

    @Transactional
    void addUser(User user);

    @Transactional
    void addStudent(@Param("userId") long userId);

    @Transactional
    void addProfessor(@Param("userId") long userId);

    User findByEmail(@Param("email") String email);

    Student findStudentByEmail(@Param("email") String email);

    Professor findProfessorByEmail(@Param("email") String email);
}
