package br.com.sonner.estagio.controller;

import br.com.sonner.estagio.controller.api.SalaController;
import br.com.sonner.estagio.dao.DiretorDAOImpl;
import br.com.sonner.estagio.dao.SalaDAOImpl;
import br.com.sonner.estagio.model.parte2.segundo.Sala;
import br.com.sonner.estagio.util.CustomException;

import java.util.List;

public class SalaControllerImpl implements SalaController {
    private SalaDAOImpl salaDAO;

    public SalaControllerImpl() {
        salaDAO = SalaDAOImpl.getInstance();
    }

    @Override
    public void save(Sala sala) {
        this.salaDAO.save(sala);

    }

    @Override
    public List<Sala> getAll() {
        return this.salaDAO.getAll();
    }

    @Override
    public Sala getOne(long id) {
        return this.salaDAO.getOne(id);
    }

    @Override
    public void update(Sala sala) {
        this.salaDAO.update(sala);

    }

    @Override
    public void delete(long id) throws CustomException {
        this.salaDAO.delete(id);

    }


}
