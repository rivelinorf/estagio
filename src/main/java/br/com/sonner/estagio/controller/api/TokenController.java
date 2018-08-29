package br.com.sonner.estagio.controller.api;

import br.com.sonner.estagio.model.Usuario;

public interface TokenController {
    void save(Usuario usuario, String token);

    Usuario getUser(String token);
}
