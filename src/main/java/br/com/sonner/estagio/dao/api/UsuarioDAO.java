package br.com.sonner.estagio.dao.api;

import br.com.sonner.estagio.model.Usuario;
import br.com.sonner.estagio.vos.UsuarioFiltroVo;

import java.util.List;

public interface UsuarioDAO {
    void save(Usuario usuario);

    Usuario efetuaLogin(Usuario usuario);

    List<Usuario> pesquisaUsuario(UsuarioFiltroVo vo);
}
