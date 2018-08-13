package br.com.sonner.estagio.controller;

import br.com.sonner.estagio.controller.api.CidadeController;
import br.com.sonner.estagio.dao.CidadeDAOImpl;
import br.com.sonner.estagio.dao.api.CidadeDAO;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.vos.CidadeFiltroVO;

import java.util.List;

public class CidadeControllerImpl implements CidadeController {
    private CidadeDAO cidadeDAO;

    public CidadeControllerImpl() {
        this.cidadeDAO = CidadeDAOImpl.getInstance();
    }

    @Override
    public void save(Cidade cidade) {
        this.cidadeDAO.save(cidade);
    }

    @Override
    public List<Cidade> getAll() {
        return this.cidadeDAO.getAll();
    }

    @Override
    public Cidade getOne(long id) {
        return this.cidadeDAO.getOne(id);
    }

    @Override
    public void update(Cidade cidade) {
        this.cidadeDAO.update(cidade);
    }

    @Override
    public void delete(long id) {
        this.cidadeDAO.delete(id);
    }

    public List<Cidade> filtrar (CidadeFiltroVO vo) {
        return this.cidadeDAO.pesquisaCidade(vo);
    }
}
