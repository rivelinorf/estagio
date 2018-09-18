package br.com.sonner.estagio.controller;

import br.com.sonner.estagio.controller.api.EscolaController;
import br.com.sonner.estagio.dao.EscolaDAOImpl;
import br.com.sonner.estagio.model.parte2.segundo.Escola;
import br.com.sonner.estagio.util.CustomException;

import java.util.List;

public class EscolaControllerImpl implements EscolaController {
    private EscolaDAOImpl escolaDAO;

    public EscolaControllerImpl() {
        escolaDAO = EscolaDAOImpl.getInstance();
    }

    @Override
    public void save(Escola escola) {
        this.escolaDAO.save(escola);

    }

    @Override
    public List<Escola> getAll() {
        return this.escolaDAO.getAll();
    }

    @Override
    public Escola getOne(long id) {
        return this.escolaDAO.getOne(id);
    }

    @Override
    public void update(Escola escola) {
        this.escolaDAO.update(escola);

    }

    @Override
    public void delete(long id) throws CustomException {
        this.escolaDAO.delete(id);

    }
}
