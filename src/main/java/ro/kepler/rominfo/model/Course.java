package ro.kepler.rominfo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Dragos on 15.05.2017.
 */
public class Course implements Serializable {
    private long courseCode;
    private String courseName;
    private String category;
    private User professor;
    private Date startTime;
    private String description;
    private int numberOfLectures;
    private List<Lecture> lectures = new ArrayList<Lecture>();
    private long professorId;


    public void setProfessorId(long professorId) {
        this.professorId = professorId;
    }

    public long getProfessorId() {
        return professorId;
    }

    public List<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(List<Lecture> lectures) {
        this.lectures = lectures;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNumberOfLectures(int numberOfLectures) {
        this.numberOfLectures = numberOfLectures;
    }

    public String getDescription() {
        return description;
    }

    public int getNumberOfLectures() {
        return numberOfLectures;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    private List<Student> students = new ArrayList<Student>();

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public long getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(long courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public User getProfessor() {
        return professor;
    }

    public void setProfessor(User professor) {
        this.professor = professor;
    }
}
