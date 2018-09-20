package br.com.sonner.estagio.controller;

import br.com.sonner.estagio.controller.api.DiretorController;
import br.com.sonner.estagio.dao.DiretorDAOImpl;
import br.com.sonner.estagio.model.Diretor;
import br.com.sonner.estagio.util.CustomException;

import java.util.List;

public class DiretorControllerImpl implements DiretorController {
    private DiretorDAOImpl diretorDAO;

    public DiretorControllerImpl() {
        diretorDAO = DiretorDAOImpl.getInstance();
    }

    @Override
    public void save(Diretor diretor) {
        this.diretorDAO.save(diretor);

    }

    @Override
    public List<Diretor> getAll() {
        return this.diretorDAO.getAll();
    }

    @Override
    public Diretor getOne(long id) {
        return this.diretorDAO.getOne(id);
    }

    @Override
    public void update(Diretor diretor) {
        this.diretorDAO.update(diretor);

    }

    @Override
    public void delete(long id) throws CustomException {
        this.diretorDAO.delete(id);

    }
}
