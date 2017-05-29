package ro.kepler.rominfo.mappers;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import ro.kepler.rominfo.model.Course;

import java.util.List;

/**
 * Created by Dragos on 15.05.2017.
 */

@Repository
public interface CourseMapper {
    Course getCourseByCode(int courseCode);
    List<Course> getAllCourses();
    List<Course> getMyCourses(@Param("studentId") int studentId);

    List<Course> getMyCoursesWithDates(@Param("studentId") int studentId);
    Course getCourseByName(String courseName);
  //  void addCourse(Course course);
}
