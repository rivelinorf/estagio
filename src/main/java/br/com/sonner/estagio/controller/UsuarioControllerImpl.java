package br.com.sonner.estagio.controller;

import br.com.sonner.estagio.controller.api.UsuarioController;
import br.com.sonner.estagio.dao.UsuarioDAOImpl;
import br.com.sonner.estagio.dao.api.UsuarioDAO;
import br.com.sonner.estagio.model.Usuario;

public class UsuarioControllerImpl implements UsuarioController{
	
	private UsuarioDAO usuarioDAO;
	
	public UsuarioControllerImpl() {
		this.usuarioDAO = UsuarioDAOImpl.getInstance();
	}
	
	@Override
	public void save(Usuario usuario) {
		this.usuarioDAO.save(usuario);
		
	}

	@Override
	public boolean efetuaLogin(Usuario usuario) {
		return this.usuarioDAO.efetuaLogin(usuario);
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
		
	}

}
