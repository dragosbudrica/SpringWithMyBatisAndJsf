package ro.kepler.rominfo.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dragos on 15.05.2017.
 */
public class Course {
    private Integer courseCode;
    private String courseName;
    private String category;
    private  Professor professor;

    public Integer getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(Integer courseCode) {
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

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
