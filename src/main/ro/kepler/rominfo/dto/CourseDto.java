package ro.kepler.rominfo.dto;

import java.io.Serializable;

/**
 * Created by Dragos on 19.05.2017.
 */
public class CourseDto implements Serializable {
    private String courseName;
    private String category;
    private String professor;

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

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }
}
