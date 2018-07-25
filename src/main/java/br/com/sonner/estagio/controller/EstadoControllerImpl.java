package br.com.sonner.estagio.controller;

import br.com.sonner.estagio.controller.api.EstadoController;
import br.com.sonner.estagio.dao.EstadoDAOImpl;
import br.com.sonner.estagio.model.Estado;

public class EstadoControllerImpl implements EstadoController {

    @Override
    public void save(Estado estado) {
        new EstadoDAOImpl().save(estado);
    }
}
