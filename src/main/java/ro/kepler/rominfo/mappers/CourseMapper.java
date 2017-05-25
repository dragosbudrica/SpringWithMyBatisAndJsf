package ro.kepler.rominfo.mappers;
import org.springframework.stereotype.Repository;
import ro.kepler.rominfo.model.Course;

import java.util.List;

/**
 * Created by Dragos on 15.05.2017.
 */

@Repository
public interface CourseMapper {
    Course getCourseByCode(Integer courseCode);
    List<Course> getAllCourses();
    Course getCourseByName(String courseName);
  //  void addCourse(Course course);
}
