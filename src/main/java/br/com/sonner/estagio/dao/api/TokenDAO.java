package br.com.sonner.estagio.dao.api;

import br.com.sonner.estagio.model.Usuario;

public interface TokenDAO {
    void save(Usuario usuario, String token);

    Usuario getUser(String token);
}
