package br.com.sonner.estagio.controller;

import br.com.sonner.estagio.controller.api.TurmaController;
import br.com.sonner.estagio.dao.TurmaDAOImpl;
import br.com.sonner.estagio.model.parte2.segundo.Turma;
import br.com.sonner.estagio.util.CustomException;

import java.util.List;

public class TurmaControllerImpl implements TurmaController {
    private TurmaDAOImpl turmaDAO;

    public TurmaControllerImpl() {
        turmaDAO = TurmaDAOImpl.getInstance();
    }

    @Override
    public void save(Turma turma) {
        this.turmaDAO.save(turma);

    }

    @Override
    public List<Turma> getAll() {
        return this.turmaDAO.getAll();
    }

    @Override
    public Turma getOne(long id) {
        return this.turmaDAO.getOne(id);
    }

    @Override
    public void update(Turma turma) {
        this.turmaDAO.update(turma);

    }

    @Override
    public void delete(long id) throws CustomException {
        this.turmaDAO.delete(id);

    }
}
