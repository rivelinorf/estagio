package br.com.sonner.estagio.dao.api;

import br.com.sonner.estagio.model.Usuario;
import br.com.sonner.estagio.vos.UsuarioFiltroVo;

import java.util.List;

public interface TokenDAO {
    void save(Usuario usuario, String token);
    Usuario getUser(String token);
}
