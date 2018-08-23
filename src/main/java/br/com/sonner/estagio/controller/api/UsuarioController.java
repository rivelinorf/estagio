package br.com.sonner.estagio.controller.api;

import br.com.sonner.estagio.model.Usuario;

import java.util.List;

public interface UsuarioController {
    void save(Usuario usuario);

    Usuario efetuaLogin(Usuario usuario);

    List<String> validation(Usuario usuario);

    List<String> validationLog(Usuario usuario);
}
