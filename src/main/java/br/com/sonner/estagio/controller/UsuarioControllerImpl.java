package br.com.sonner.estagio.controller;

import br.com.sonner.estagio.controller.api.UsuarioController;
import br.com.sonner.estagio.dao.UsuarioDAOImpl;
import br.com.sonner.estagio.dao.api.UsuarioDAO;
import br.com.sonner.estagio.model.Usuario;
import br.com.sonner.estagio.vos.UsuarioFiltroVo;

import java.util.ArrayList;
import java.util.List;

public class UsuarioControllerImpl implements UsuarioController {

    private UsuarioDAO usuarioDAO;

    public UsuarioControllerImpl() {
        this.usuarioDAO = UsuarioDAOImpl.getInstance();
    }

    @Override
    public void save(Usuario usuario) {
        this.usuarioDAO.save(usuario);
    }

    public void update(Usuario usuario) {
        this.usuarioDAO.update(usuario);
    }

    @Override
    public Usuario efetuaLogin(Usuario usuario) {
        return this.usuarioDAO.efetuaLogin(usuario);
    }

    @Override
    public List<String> validation(Usuario usuario) {
        List<String> erros = new ArrayList<>();

        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            erros.add("O email nao pode ser vazio");
        }

        if (usuario.getSenha().length() < 8) {
            erros.add("A senha necessita ter mais que 8 caracteres");
        }

        UsuarioFiltroVo vo = new UsuarioFiltroVo();
        vo.setEmail(usuario.getEmail());
        vo.setUsuario(usuario.getUsuario());

        List<Usuario> listaUsuarios = this.filtrar(vo);

        if (listaUsuarios.size() > 0) {
            erros.add("Usuario ja cadastrado");
        }

        return erros;
    }

    public List<String> redefSenha(String newPass, String confimPass) {
        List<String> erros = new ArrayList<>();

        if (!newPass.equals(confimPass)) {
            erros.add("Senhas diferentes");
        }

        if (newPass.length() < 8 || confimPass.length() < 8) {
            erros.add("As senhas necessitam ter mais que 8 caracteres");
        }

        return erros;
    }


    @Override
    public List<Usuario> filtrar(UsuarioFiltroVo vo) {
        return this.usuarioDAO.pesquisaUsuario(vo);
    }


}
