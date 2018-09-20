package br.com.sonner.estagio.controller;

import br.com.sonner.estagio.controller.api.MatriculaController;
import br.com.sonner.estagio.dao.MatriculaDAOImpl;
import br.com.sonner.estagio.model.Matricula;
import br.com.sonner.estagio.util.CustomException;

import java.util.List;

public class MatriculaControllerImpl implements MatriculaController {
    private MatriculaDAOImpl matriculaDAO;

    public MatriculaControllerImpl() {
        matriculaDAO = MatriculaDAOImpl.getInstance();
    }

    @Override
    public void save(Matricula matricula) {
        this.matriculaDAO.save(matricula);

    }

    @Override
    public List<Matricula> getAll() {
        return this.matriculaDAO.getAll();
    }

    @Override
    public Matricula getOne(long id) {
        return this.matriculaDAO.getOne(id);
    }

    @Override
    public void update(Matricula matricula) {
        this.matriculaDAO.update(matricula);

    }

    @Override
    public void delete(long id) throws CustomException {
        this.matriculaDAO.delete(id);

    }

    @Override
    public List<String> validation(Matricula matricula) {
        return null;
    }
}