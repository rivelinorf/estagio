package br.com.sonner.estagio.servlet.usuario;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sonner.estagio.controller.UsuarioControllerImpl;
import br.com.sonner.estagio.controller.api.UsuarioController;
import br.com.sonner.estagio.model.Usuario;

@WebServlet("/logar")
public class Login extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String usuario = request.getParameter("usuario");
		String senha = request.getParameter("senha");
		
		RequestDispatcher rd = null;
		
		Usuario user = new Usuario();
		user.setUsuario(usuario);
		user.setSenha(senha);
		
		UsuarioController usuarioController = new UsuarioControllerImpl();

		if (usuarioController.efetuaLogin(user)) {
			HttpSession sessao = request.getSession();
			sessao.setAttribute("USER", usuario);
			
			rd = request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);
		} else {
			request.setAttribute("msg", "Usuário ou senha inválidos");
			rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
	}

}
