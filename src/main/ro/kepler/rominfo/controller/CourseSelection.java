package ro.kepler.rominfo.controller;

import ro.kepler.rominfo.dto.CourseDto;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

/**
 * Created by Dragos on 19.05.2017.
 */

public class CourseSelection implements Serializable{

    private CourseDto selectedCourse;

    public CourseDto getSelectedCourse() {
        return selectedCourse;
    }

    public void setSelectedCourse(CourseDto selectedCourse) {
        this.selectedCourse = selectedCourse;
    }
}
