package br.com.sonner.estagio.servlet.endereco;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sonner.estagio.controller.EnderecoControllerImpl;

@WebServlet("/endereco-deleta")
public class Deleta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		new EnderecoControllerImpl().delete(Integer.parseInt(req.getParameter("id")));

		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/endereco/lista.jsp");
		requestDispatcher.forward(req, res);
	}

}
