package br.com.sonner.estagio.controller;

import br.com.sonner.estagio.controller.api.LogradouroController;
import br.com.sonner.estagio.dao.LogradouroDAOImpl;
import br.com.sonner.estagio.dao.api.LogradouroDAO;
import br.com.sonner.estagio.model.Logradouro;
import br.com.sonner.estagio.vos.LogradouroFiltroVO;

import java.util.ArrayList;
import java.util.List;

public class LogradouroControllerImpl implements LogradouroController {

    private LogradouroDAO logradouroDAO;

    public LogradouroControllerImpl() {
        this.logradouroDAO = LogradouroDAOImpl.getIntance();
    }

    @Override
    public Logradouro save(Logradouro logradouro) {
        logradouro = this.logradouroDAO.save(logradouro);
        return logradouro;
    }

    @Override
    public List<Logradouro> getAll() {
        return this.logradouroDAO.getAll();
    }

    @Override
    public Logradouro getOne(long id) {
        return this.logradouroDAO.getOne(id);
    }

    @Override
    public void update(Logradouro logradouro) {
        this.logradouroDAO.update(logradouro);
    }

    @Override
    public void delete(long id) {
        this.logradouroDAO.delete(id);

    }

    @Override
    public List<String> validation(Logradouro logradouro) {
        List<String> erros = new ArrayList<>();

        if (logradouro.getNome() == null || logradouro.getNome().isEmpty()) {
            erros.add("O nome do logradouro não pode ser vazio");
        }


        if (logradouro.getNome().length() > 50) {
            erros.add("O nome do logradouro não pode exceder 50 caracteres  ");
        }

        if (logradouro.getTipologradouro() == null) {
            erros.add("Impossível ter um logradouro sem um tipo de logradouro selecionado");
        }

        if (logradouro.getCidade() == null) {
            erros.add("Impossível ter um logradouro sem uma cidade selecionada");
        }
        return erros;
    }


    public List<Logradouro> filtrar(LogradouroFiltroVO vo) {
        return this.logradouroDAO.pesquisaLogradouro(vo);

    }

    public List<Logradouro> filtrarLike(LogradouroFiltroVO vo) {
        return this.logradouroDAO.pesquisaLogradouroLike(vo);
    }
}
