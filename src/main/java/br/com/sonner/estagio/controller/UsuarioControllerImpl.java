package br.com.sonner.estagio.controller;

import br.com.sonner.estagio.controller.api.UsuarioController;
import br.com.sonner.estagio.dao.UsuarioDAOImpl;
import br.com.sonner.estagio.dao.api.UsuarioDAO;
import br.com.sonner.estagio.model.Usuario;

import java.util.ArrayList;
import java.util.List;

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
	public Usuario efetuaLogin(Usuario usuario) {
		return this.usuarioDAO.efetuaLogin(usuario);
	}

	@Override
	public List<String> validation(Usuario usuario) {
		List<String> erros = new ArrayList<>();

		if(usuario.getSenha().length() < 8){
			erros.add("A senha necessita ter mais que 8 caracteres");
		}

		if(usuario.getEmail().equals(null) ||usuario.getEmail().isEmpty()){
			erros.add("O email nao pode ser vazio");
		}

		//if(usuario.getUsuario().equals(null)||usuario.getUsuario().isEmpty()){
		//	erros.add("O usuario nao pode ser vazio");
		//}

		return erros;
	}

	@Override
	public List<String> validationLog(Usuario usuario) {
		List<String> erros = new ArrayList<>();

		if(usuario.getSenha().length() < 8){
			erros.add("A senha necessita ter mais que 8 caracteres");
		}

		if(usuario.getUsuario().equals(null)||usuario.getUsuario().isEmpty()){
			erros.add("O usuario nao pode ser vazio");
		}

		return erros;
	}



}
