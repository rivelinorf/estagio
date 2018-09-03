package br.com.sonner.estagio.controller;

import br.com.sonner.estagio.controller.api.EstadoController;
import br.com.sonner.estagio.dao.EstadoDAOImpl;
import br.com.sonner.estagio.model.Estado;
import br.com.sonner.estagio.vos.EstadoFiltroVO;

import java.util.ArrayList;
import java.util.List;

public class EstadoControllerImpl implements EstadoController {
    private EstadoDAOImpl estadoDAO;

    public EstadoControllerImpl() {
        this.estadoDAO = (EstadoDAOImpl) EstadoDAOImpl.getInstance();
    }

    @Override
    public void save(Estado estado) {
        this.estadoDAO.save(estado);
    }

    @Override
    public List<Estado> getAll() {
        return this.estadoDAO.getAll();
    }

    @Override
    public Estado getOne(long id) {
        return this.estadoDAO.getOne(id);
    }

    @Override
    public void update(Estado estado) {
        this.estadoDAO.update(estado);
    }

    @Override
    public void delete(long id) {
        this.estadoDAO.delete(id);
    }

    @Override
    public List<String> validation(Estado estado) {
        List<String> erros = new ArrayList<>();

        if (estado.getNome().length() == 0) {
            erros.add("Não é possível ter um estado sem nome");
        }

        if (estado.getNome().length() > 50) {
            erros.add("Nome do estado não pode exceder 50 caracteres");
        }

        if (estado.getAbv().length() == 0) {
            erros.add("Não é possível ter um estado sem abreviação");
        }

        if (estado.getAbv().length() > 5) {
            erros.add("Abreviação do estado não pode exceder 5 caracteres");
        }

        return erros;
    }

    public List<Estado> filtrar(EstadoFiltroVO estadosPesquisados) {
        return this.estadoDAO.pesquisaEstado(estadosPesquisados.getEstado(), estadosPesquisados.getAbv());
    }

    public List<Estado> filtrarLike(EstadoFiltroVO estadosPesquisados) {
        return this.estadoDAO.pesquisaEstadoLike(estadosPesquisados.getEstado(), estadosPesquisados.getAbv());
    }
}
