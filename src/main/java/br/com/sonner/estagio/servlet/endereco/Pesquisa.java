package br.com.sonner.estagio.servlet.endereco;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sonner.estagio.controller.EnderecoControllerImpl;

@WebServlet("/pesquisa-endereco")
public class Pesquisa extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EnderecoControllerImpl enderecoController = new EnderecoControllerImpl();
		enderecoController.setEnderecosPesquisados(request.getParameter("cep"));
	}
}
