package br.com.sonner.estagio.servlet.usuario;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sonner.estagio.controller.UsuarioControllerImpl;
import br.com.sonner.estagio.controller.api.UsuarioController;
import br.com.sonner.estagio.model.Usuario;

@SuppressWarnings("serial")
@WebServlet("/usuario-insere")
public class Insere extends HttpServlet {


	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		UsuarioController usuarioController = new UsuarioControllerImpl();
		
		Usuario novo = new Usuario();
		
		novo.setNome(req.getParameter("nome"));
		novo.setUsuario(req.getParameter("usuario"));
		novo.setSenha(req.getParameter("senha"));
		
		usuarioController.save(novo);
		
		RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
		rd.forward(req, res);
	}
}
