package ro.kepler.rominfo.mapper;

import org.apache.ibatis.annotations.Param;
import ro.kepler.rominfo.model.Course;
import ro.kepler.rominfo.model.Student;

/**
 * Created by Dragos on 22.05.2017.
 */
public interface EnrollmentMapper {
    void addEnrollment(@Param("studentId") Integer studentId, @Param("courseCode") Integer courseCode);
}
