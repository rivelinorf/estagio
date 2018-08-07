package br.com.sonner.estagio.controller.api;

import br.com.sonner.estagio.model.Usuario;

public interface UsuarioController {
	void save(Usuario usuario);
	boolean efetuaLogin(Usuario usuario);
	void logout();
}
