package br.com.sonner.estagio.servlet.bairro;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sonner.estagio.controller.BairroControllerImpl;

@WebServlet("/bairro-deleta")
public class Deleta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		new BairroControllerImpl().delete(Long.valueOf(req.getParameter("id")));

		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/bairro/lista.jsp");
		requestDispatcher.forward(req, res);

	}

}
