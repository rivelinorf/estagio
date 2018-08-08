package br.com.sonner.estagio.dao.api;

import br.com.sonner.estagio.model.Usuario;

public interface UsuarioDAO {
	void save(Usuario usuario);
	Usuario efetuaLogin(Usuario usuario);


}
