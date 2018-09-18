package br.com.sonner.estagio.controller;


import br.com.sonner.estagio.controller.api.DisciplinaController;
import br.com.sonner.estagio.dao.DisciplinaDAOImpl;
import br.com.sonner.estagio.model.parte2.segundo.Disciplina;
import br.com.sonner.estagio.util.CustomException;

import java.util.List;

public class DisciplinaControllerImpl implements DisciplinaController {
    private DisciplinaDAOImpl disciplinaDAO;

    public DisciplinaControllerImpl() {
        disciplinaDAO = DisciplinaDAOImpl.getInstance();
    }

    @Override
    public void save(Disciplina disciplina) {
        this.disciplinaDAO.save(disciplina);

    }

    @Override
    public List<Disciplina> getAll() {
        return this.disciplinaDAO.getAll();
    }

    @Override
    public Disciplina getOne(long id) {
        return this.disciplinaDAO.getOne(id);
    }

    @Override
    public void update(Disciplina disciplina) {
        this.disciplinaDAO.update(disciplina);

    }

    @Override
    public void delete(long id) throws CustomException {
        this.disciplinaDAO.delete(id);

    }
}
