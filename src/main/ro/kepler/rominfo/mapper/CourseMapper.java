package ro.kepler.rominfo.mapper;
import ro.kepler.rominfo.model.Course;
import java.util.List;

/**
 * Created by Dragos on 15.05.2017.
 */
public interface CourseMapper {
    Course getCourseByCode(Integer courseCode);
    List<Course> getAllCourses();
  //  void addCourse(Course course);
}
