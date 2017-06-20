package ro.kepler.rominfo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.kepler.rominfo.mappers.CourseMapper;
import ro.kepler.rominfo.mappers.LectureMapper;
import ro.kepler.rominfo.mappers.UserMapper;
import ro.kepler.rominfo.model.Course;
import ro.kepler.rominfo.model.Professor;
import ro.kepler.rominfo.model.Student;
import ro.kepler.rominfo.model.User;

import java.util.Date;
import java.util.List;

/**
 * Created by Dragos on 19.05.2017.
 */

@Service
public class CourseService {

    private CourseMapper courseMapper;
    private UserMapper userMapper;
    private LectureMapper lectureMapper;

    @Autowired
    public CourseService(UserMapper userMapper, CourseMapper courseMapper, LectureMapper lectureMapper) {
        this.userMapper = userMapper;
        this.courseMapper = courseMapper;
        this.lectureMapper = lectureMapper;
    }

    public List<Course> getAllCourses() {
        return courseMapper.getAllCourses();
    }

    public List<Course> getAllCoursesWithDates() {
        return courseMapper.getAllCoursesWithDates();
    }

    public List<Course> getStudentCourses(String email) {
        Student student = userMapper.findStudentByEmail(email);
        return courseMapper.getStudentCourses(student.getStudentId());
    }

    public List<Course> getProfessorCourses(String email) {
        Professor professor = userMapper.findProfessorByEmail(email);
        return courseMapper.getProfessorCourses(professor.getProfessorId());
    }

    public Course getCourseByName(String courseName) {
        return courseMapper.getCourseByName(courseName);
    }

    public void setTime(String courseName, Date startTime) {
        courseMapper.updateCourseSchedule(courseName, startTime);
    }

    public void addCourse(String courseName, String category, int numberOfLectures, String description, String email) {
        Professor professor = userMapper.findProfessorByEmail(email);
        Course course = new Course();
        course.setCourseName(courseName);
        course.setCategory(category);
        course.setNumberOfLectures(numberOfLectures);
        course.setDescription(description);
        course.setProfessorId(professor.getProfessorId());
        courseMapper.addCourse(course);
     //   courseMapper.addCourse(course.getCourseName(), course.getCategory(), course.getNumberOfLectures(), course.getDescription(), professor.getProfessorId());
        for(int i = 0; i < numberOfLectures; i++) {
            lectureMapper.createLecture(course.getCourseCode(), "Lecture " + (i+1));
        }
    }

}
