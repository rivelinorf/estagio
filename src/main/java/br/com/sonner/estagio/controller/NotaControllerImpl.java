package br.com.sonner.estagio.controller;

import br.com.sonner.estagio.controller.api.NotaController;
import br.com.sonner.estagio.dao.NotaDAOImpl;
import br.com.sonner.estagio.model.parte2.segundo.Nota;
import br.com.sonner.estagio.util.CustomException;

import java.util.List;

public class NotaControllerImpl implements NotaController {
    private NotaDAOImpl notaDAO;

    public NotaControllerImpl() {
        notaDAO = NotaDAOImpl.getInstance();
    }

    @Override
    public void save(Nota nota) {
        this.notaDAO.save(nota);

    }

    @Override
    public List<Nota> getAll() {
        return this.notaDAO.getAll();
    }

    @Override
    public Nota getOne(long id) {
        return this.notaDAO.getOne(id);
    }

    @Override
    public void update(Nota nota) {
        this.notaDAO.update(nota);

    }

    @Override
    public void delete(long id) throws CustomException {
        this.notaDAO.delete(id);

    }
}
