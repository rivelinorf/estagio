package br.com.sonner.estagio.controller;

import br.com.sonner.estagio.controller.api.CidadeController;
import br.com.sonner.estagio.dao.CidadeDAOImpl;
import br.com.sonner.estagio.dao.api.CidadeDAO;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.CidadeFiltroVO;

import java.util.ArrayList;
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
    public void delete(long id) throws CustomException {
        this.cidadeDAO.delete(id);
    }

    @Override
    public List<String> validation(Cidade cidade) {
        List<String> erros = new ArrayList<>();

        if (cidade.getNome().length() == 0) {
            erros.add("Não é possivel ter uma cidade sem nome");
        }

        if (cidade.getNome().length() > 50) {
            erros.add("Nome da cidade não pode exceder 50 caracteres");
        }

        if (cidade.getCod().length() == 0) {
            erros.add("Não é possivel ter cidade sem sigla");
        }

        if (cidade.getCod().length() > 2) {
            erros.add("Código da cidade não pode exceder 2 caracteres");
        }

        if (cidade.getCep().length() == 0) {
            erros.add("Não é possível ter uma cidade sem CEP");
        }

        if (cidade.getCep().length() < 10 && cidade.getCep().length() > 0) {
            erros.add("Cep inválido");
        }

        if (cidade.getEstado() == null) {
            erros.add("Impossível ter uma cidade sem estado");
        }

        return erros;
    }

    public List<Cidade> filtrar(CidadeFiltroVO vo) {
        return this.cidadeDAO.pesquisaCidade(vo);
    }

    public List<Cidade> filtrarLike(CidadeFiltroVO vo) {
        return this.cidadeDAO.pesquisaCidadeLike(vo);
    }

}
