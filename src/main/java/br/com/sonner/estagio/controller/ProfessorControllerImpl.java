package br.com.sonner.estagio.controller;

import br.com.sonner.estagio.controller.api.ProfessorController;
import br.com.sonner.estagio.dao.ProfessorDAOImpl;
import br.com.sonner.estagio.model.Professor;
import br.com.sonner.estagio.util.CustomException;

import java.util.List;

public class ProfessorControllerImpl implements ProfessorController {
    private ProfessorDAOImpl professorDAO;

    public ProfessorControllerImpl() {
        professorDAO = ProfessorDAOImpl.getInstance();
    }

    @Override
    public void save(Professor professor) {
        this.professorDAO.save(professor);

    }

    @Override
    public List<Professor> getAll() {
        return this.professorDAO.getAll();
    }

    @Override
    public Professor getOne(long id) {
        return this.professorDAO.getOne(id);
    }

    @Override
    public void update(Professor professor) {
        this.professorDAO.update(professor);

    }

    @Override
    public void delete(long id) throws CustomException {
        this.professorDAO.delete(id);

    }
}
