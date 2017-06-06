package ro.kepler.rominfo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.kepler.rominfo.mappers.CourseMapper;
import ro.kepler.rominfo.mappers.UserMapper;
import ro.kepler.rominfo.model.Course;
import ro.kepler.rominfo.model.Professor;
import ro.kepler.rominfo.model.Student;

import java.util.Date;
import java.util.List;

/**
 * Created by Dragos on 19.05.2017.
 */

@Service
public class CourseService {

    private CourseMapper courseMapper;
    private UserMapper studentMapper;
    private UserMapper professorMapper;

    @Autowired
    public CourseService(UserMapper professorMapper, CourseMapper courseMapper, UserMapper studentMapper) {
        this.professorMapper = professorMapper;
        this.courseMapper = courseMapper;
        this.studentMapper = studentMapper;
    }

    public List<Course> getAllCourses() {
        return courseMapper.getAllCourses();
    }

    public List<Course> getAllCoursesWithDates() {
        return courseMapper.getAllCoursesWithDates();
    }

    public List<Course> getStudentCoursesWithDates(String email) {
        Student student = (Student)studentMapper.findByEmail(email);
        return courseMapper.getStudentCoursesWithDates(student.getUserId());
    }

    public List<Course> getStudentCourses(String email) {
        Student student = (Student)studentMapper.findByEmail(email);
        return courseMapper.getStudentCourses(student.getUserId());
    }

    public List<Course> getProfessorCourses(String email) {
        Professor professor = (Professor)professorMapper.findByEmail(email);
        return courseMapper.getProfessorCourses(professor.getUserId());
    }

    public Course getCourseByName(String courseName) {
        return courseMapper.getCourseByName(courseName);
    }

    public void setTime(String courseName, Date startTime, Date endTime) {
        courseMapper.updateCourseSchedule(courseName, startTime, endTime);
    }

    public void addCourse(String courseName, String category, String email) {
        Professor professor = (Professor)professorMapper.findByEmail(email);
        courseMapper.addCourse(courseName, category, professor.getUserId());
    }

}
