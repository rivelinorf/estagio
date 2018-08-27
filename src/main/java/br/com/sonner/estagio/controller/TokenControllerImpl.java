package br.com.sonner.estagio.controller;

import br.com.sonner.estagio.controller.api.TokenController;
import br.com.sonner.estagio.controller.api.UsuarioController;
import br.com.sonner.estagio.dao.TokenDAOImpl;
import br.com.sonner.estagio.dao.UsuarioDAOImpl;
import br.com.sonner.estagio.dao.api.TokenDAO;
import br.com.sonner.estagio.dao.api.UsuarioDAO;
import br.com.sonner.estagio.model.Usuario;
import br.com.sonner.estagio.vos.UsuarioFiltroVo;

import java.util.ArrayList;
import java.util.List;

public class TokenControllerImpl implements TokenController {

    private TokenDAO tokenDAO;

    public TokenControllerImpl() {
        this.tokenDAO = TokenDAOImpl.getInstance();
    }

    @Override
    public void save(Usuario usuario, String token) {
        this.tokenDAO.save(usuario, token);
    }

    @Override
    public Usuario getUser(String token) {
        return this.tokenDAO.getUser(token);
    }
}
