package ro.kepler.rominfo.mappers;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ro.kepler.rominfo.model.User;

import java.util.List;

/**
 * Created by Dragos on 31.05.2017.
 */

@Repository
public interface UserMapper {

    @Transactional(propagation = Propagation.SUPPORTS)
    List<User> getAllUsers();

    @Transactional
    void addProfessorUser(User user);

    @Transactional
    void addStudentUser(User user);

    User findByEmail(@Param("email") String email);
}
