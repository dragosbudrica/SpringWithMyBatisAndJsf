package ro.kepler.rominfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.kepler.rominfo.mappers.ProfessorMapper;
import ro.kepler.rominfo.model.Professor;

/**
 * Created by Dragos on 29.05.2017.
 */

@Service
public class ProfessorService {
    private ProfessorMapper professorMapper;

    @Autowired
    public ProfessorService(ProfessorMapper professorMapper) {
        this.professorMapper = professorMapper;
    }

    @Transactional
    public void addProfessor(String firstName, String lastName, long ssn, String email, String password) {
        Professor professor = new Professor();
        professor.setFirstName(firstName);
        professor.setLastName(lastName);
        professor.setSsn(ssn);
        professor.setEmail(email);
        professor.setPassword(password);
        professorMapper.addProfessor(professor);
    }

    public boolean find(String email) {
        Professor professor =  professorMapper.findByEmail(email);
        return professor != null;
    }

    public Professor getProfessorByEmail(String email) {
        return professorMapper.findByEmail(email);
    }

}
