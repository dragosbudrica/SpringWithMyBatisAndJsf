package ro.kepler.rominfo.mappers;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import ro.kepler.rominfo.model.Course;
import ro.kepler.rominfo.model.Professor;

import java.util.List;

/**
 * Created by Dragos on 29.05.2017.
 */

@Repository
public interface ProfessorMapper {
    void addProfessor(Professor professor);
    Professor findByEmail(@Param("email") String email);
}
