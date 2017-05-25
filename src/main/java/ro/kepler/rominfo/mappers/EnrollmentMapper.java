package  ro.kepler.rominfo.mappers;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import ro.kepler.rominfo.model.Course;

import java.util.List;

/**
 * Created by Dragos on 22.05.2017.
 */

@Repository
public interface EnrollmentMapper {
    void addEnrollment(@Param("studentId") int studentId, @Param("courseCode") int courseCode);
    List<Course> getCoursesOfStudent(@Param("studentId") int studentId);
}
