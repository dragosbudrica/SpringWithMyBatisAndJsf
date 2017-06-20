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
    List<Course> getStudentCourses(@Param("studentId") long studentId);
    List<Course> getStudentCoursesWithDates(@Param("studentId") long studentId);
    List<Course> getProfessorCourses(@Param("professorId") long professorId);
    List<Course> getAllCoursesWithDates();
    Course getCourseByName(@Param("courseName") String courseName);
    void updateCourseSchedule(@Param("courseName") String courseName, @Param("startTime") Date startTime);
 //   void addCourse(@Param("courseName")String courseName, @Param("category") String category, @Param("numberOfLectures") int numberOfLectures, @Param("description") String description, @Param("professorId") int professorId);
    void addCourse(@Param("course")Course course);
}
