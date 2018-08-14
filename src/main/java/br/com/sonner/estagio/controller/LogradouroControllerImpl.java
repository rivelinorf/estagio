package br.com.sonner.estagio.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.sonner.estagio.controller.api.LogradouroController;
import br.com.sonner.estagio.dao.CidadeDAOImpl;
import br.com.sonner.estagio.dao.LogradouroDAOImpl;
import br.com.sonner.estagio.dao.TipoLogradouroDAOImpl;
import br.com.sonner.estagio.dao.api.CidadeDAO;
import br.com.sonner.estagio.dao.api.LogradouroDAO;
import br.com.sonner.estagio.dao.api.TipoLogradouroDAO;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.model.Logradouro;
import br.com.sonner.estagio.model.TipoLogradouro;
import br.com.sonner.estagio.vos.CidadeFiltroVO;
import br.com.sonner.estagio.vos.LogradouroFiltroVO;

public class LogradouroControllerImpl implements LogradouroController {

    private LogradouroDAO logradouroDAO;

    public LogradouroControllerImpl() {
        this.logradouroDAO = LogradouroDAOImpl.getIntance();
    }

    @Override
    public void save(Logradouro logradouro) {
        this.logradouroDAO.save(logradouro);
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

        if (logradouro.getNome().length() == 0) {
            erros.add("Nao é possivel inserir um logradouro sem nome ");
        }
        if (logradouro.getTipologradouro() == null) {
            erros.add("Impossível ter um logradouro sem um tipo de logradouro selecionada");
        }
        if (logradouro.getCidade() == null) {
            erros.add("Impossível ter um logradouro sem uma cidade selecionada");
        }
        return erros;
    }


    public List<Logradouro> filtrar(LogradouroFiltroVO vo) {
        return this.logradouroDAO.pesquisaLogradouro(vo);

    }


}
