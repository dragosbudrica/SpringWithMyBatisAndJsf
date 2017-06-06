package ro.kepler.rominfo.mappers;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import ro.kepler.rominfo.model.Course;

import java.util.Date;
import java.util.List;

/**
 * Created by Dragos on 15.05.2017.
 */

@Repository
public interface CourseMapper {
    Course getCourseByCode(int courseCode);
    List<Course> getAllCourses();
    List<Course> getStudentCourses(@Param("studentId") int studentId);
    List<Course> getStudentCoursesWithDates(@Param("studentId") int studentId);
    List<Course> getProfessorCourses(@Param("userId") int userId);
    List<Course> getAllCoursesWithDates();
    Course getCourseByName(String courseName);
    void updateCourseSchedule(@Param("courseName") String courseName, @Param("startTime") Date startTime, @Param("endTime") Date endTime);
    void addCourse(@Param("courseName")String courseName, @Param("category") String category, @Param("professorId") int professorId);
}
