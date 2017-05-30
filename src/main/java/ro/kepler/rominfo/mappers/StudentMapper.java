package  ro.kepler.rominfo.mappers;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import ro.kepler.rominfo.model.Student;

/**
 * Created by Dragos on 15.05.2017.
 */

@Repository
public interface StudentMapper {
    void addStudent(Student student);
    Student findByEmail(@Param("email") String email);
}
