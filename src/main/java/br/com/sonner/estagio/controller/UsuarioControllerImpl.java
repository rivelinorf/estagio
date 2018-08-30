package br.com.sonner.estagio.controller;

import br.com.sonner.estagio.controller.api.UsuarioController;
import br.com.sonner.estagio.dao.UsuarioDAOImpl;
import br.com.sonner.estagio.dao.api.UsuarioDAO;
import br.com.sonner.estagio.model.Usuario;
import br.com.sonner.estagio.vos.UsuarioFiltroVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
            erros.add("O email não pode ser vazio");
        }

        if (usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
            erros.add("A senha não pode ser vazia");
        }

        if (usuario.getUsuario() == null || usuario.getUsuario().isEmpty()) {
            erros.add("O usuário não pode ser vazio");
        }

        if (usuario.getSenha() != null && usuario.getSenha().length() > 30) {
            erros.add("A senha não pode ter mais que 30 caracteres");
        }

        if (usuario.getSenha() != null && usuario.getSenha().length() < 8) {
            erros.add("A senha não pode ter menos que 8 caracteres");
        }

        if (usuario.getUsuario() != null && usuario.getUsuario().length() > 25) {
            erros.add("Usuário não pode ter mais que 25 caracteres");
        }

        if (usuario.getUsuario() != null && usuario.getUsuario().length() < 4) {
            erros.add("Usuário não pode ter menos que 4 caracteres");
        }

        if (usuario.getEmail() != null && usuario.getEmail().length() > 50) {
            erros.add("O email não pode ter mais que 50 caracteres");
        }

        UsuarioFiltroVo vo = new UsuarioFiltroVo();
        vo.setEmail(usuario.getEmail());
        vo.setUsuario(usuario.getUsuario());

        List<Usuario> listaUsuarios = this.filtrar(vo);

        if (listaUsuarios.size() > 0) {
            erros.add("Usuário já cadastrado");
        }

        return erros;
    }

    public List<String> redefSenha(String newPass, String confimPass) {
        List<String> erros = new ArrayList<>();

        if (!Objects.equals(newPass, confimPass)) {
            erros.add("Senhas diferentes");
        }

        if (newPass.length() < 8 || confimPass.length() < 8) {
            erros.add("As senhas necessitam ter no mínimo 8 caracteres");
        }

        return erros;
    }


    @Override
    public List<Usuario> filtrar(UsuarioFiltroVo vo) {
        return this.usuarioDAO.pesquisaUsuario(vo);
    }

}
