package br.com.sonner.estagio.controller;

import br.com.sonner.estagio.controller.api.EstadoController;
import br.com.sonner.estagio.dao.EstadoDAOImpl;
import br.com.sonner.estagio.dao.api.EstadoDAO;
import br.com.sonner.estagio.model.Estado;
import br.com.sonner.estagio.vos.EstadoFiltroVO;

import java.util.List;

public class EstadoControllerImpl implements EstadoController {
    private EstadoDAO estadoDAO;

    public EstadoControllerImpl() {
        this.estadoDAO = EstadoDAOImpl.getInstance();
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


    public List<Estado> filtrar(EstadoFiltroVO estadosPesquisados) {
        return this.estadoDAO.pesquisaEstado(estadosPesquisados.getEstado(), estadosPesquisados.getAbv());
    }
}
