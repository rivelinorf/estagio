package br.com.sonner.estagio.controller;

import br.com.sonner.estagio.controller.api.TipoLogradouroController;
import br.com.sonner.estagio.dao.TipoLogradouroDAOImpl;
import br.com.sonner.estagio.model.TipoLogradouro;
import br.com.sonner.estagio.vos.TipologradouroFiltroVO;

import java.util.ArrayList;
import java.util.List;

public class TipoLogradouroControllerImpl implements TipoLogradouroController {
    private TipoLogradouroDAOImpl tDAO;

    public TipoLogradouroControllerImpl() {
        this.tDAO = (TipoLogradouroDAOImpl) TipoLogradouroDAOImpl.getInstance();
    }

    @Override
    public void save(TipoLogradouro tipoLogradouro) {
        this.tDAO.save(tipoLogradouro);
    }

    @Override
    public List<TipoLogradouro> getAll() {
        return this.tDAO.getAll();
    }

    @Override
    public TipoLogradouro getOne(long id) {
        return this.tDAO.getOne(id);
    }

    @Override
    public void update(TipoLogradouro tipoLogradouro) {
        this.tDAO.update(tipoLogradouro);

    }

    @Override
    public void delete(long id) {
        this.tDAO.delete(id);
    }

    @Override
    public List<String> validation(TipoLogradouro tipoLogradouro) {

        List<String> erros = new ArrayList<>();

        if (tipoLogradouro.getNome().length() == 0) {
            erros.add("Nao é possível inserir um tipo de logradouro sem nome ");
        }

        if (tipoLogradouro.getNome().length() > 20) {
            erros.add("O tipo de logradouro não pode exceder 20 caracteres ");
        }

        return erros;

    }


    public List<TipoLogradouro> filtrar(TipologradouroFiltroVO vo) {
        return this.tDAO.pesquisaTipoLogradouro((vo.getNome()));
    }

    public List<TipoLogradouro> filtrarLike(TipologradouroFiltroVO vo) {
        return this.tDAO.pesquisaTipoLogradouroLike(vo);
    }
}
