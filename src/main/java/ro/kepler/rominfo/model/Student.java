package ro.kepler.rominfo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dragos on 15.05.2017.
 */
public class Student {
    private long studentId;
    private int yearOfStudy;
    private String specialization;

    private List<Course> courses = new ArrayList<Course>();

    public List<Course> getCourses() {
        return courses;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
