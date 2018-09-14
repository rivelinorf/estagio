package br.com.sonner.estagio.controller;

import br.com.sonner.estagio.controller.api.BairroController;
import br.com.sonner.estagio.dao.BairroDAOImpl;
import br.com.sonner.estagio.model.Bairro;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.BairroFiltroVO;

import java.util.ArrayList;
import java.util.List;

public class BairroControllerImpl implements BairroController {

    private BairroDAOImpl bDAO;

    public BairroControllerImpl() {
        bDAO = BairroDAOImpl.getInstance();
    }

    @Override
    public void save(Bairro bairro) {
        this.bDAO.save(bairro);
    }

    @Override
    public List<Bairro> getAll() {
        return this.bDAO.getAll();
    }

    @Override
    public Bairro getOne(long id) {
        return this.bDAO.getOne(id);
    }

    @Override
    public void update(Bairro bairro) {
        this.bDAO.update(bairro);

    }

    @Override
    public void delete(long id) throws CustomException {
        this.bDAO.delete(id);

    }

    @Override
    public List<Bairro> filtrar(BairroFiltroVO vo) {
        return this.bDAO.pesquisaBairro(vo);
    }

    @Override
    public List<String> validation(Bairro bairro) {
        List<String> erros = new ArrayList<>();

        if (bairro.getNome().length() == 0) {
            erros.add("Não é possivel ter uma bairro sem nome");
        }

        if (bairro.getNome().length() > 50) {
            erros.add("Nome do bairro não pode exceder 50 caracteres");
        }

        if (bairro.getCidade() == null) {
            erros.add("Impossível ter um bairro sem cidade");
        }

        return erros;
    }

    @Override
    public List<Bairro> filtrarLike(BairroFiltroVO vo) {
        return this.bDAO.pesquisaBairroLike(vo);
    }


}
