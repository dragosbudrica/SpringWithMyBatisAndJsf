package ro.kepler.rominfo.model;

import java.io.Serializable;

/**
 * Created by Dragos on 19.05.2017.
 */
public class Professor {
    private int professorId;
    private String entitling;
    private String department;

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    public String getEntitling() {
        return entitling;
    }

    public void setEntitling(String entitling) {
        this.entitling = entitling;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

}
