package br.com.sonner.estagio.servlet.logradouro;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sonner.estagio.controller.BairroControllerImpl;
import br.com.sonner.estagio.controller.LogradouroControllerImpl;

@WebServlet("/logradouro-deleta")
public class Deleta extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		new LogradouroControllerImpl().delete(Long.valueOf(req.getParameter("id")));
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/logradouro/lista.jsp");
		requestDispatcher.forward(req, res);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		new LogradouroControllerImpl().delete(Long.valueOf(request.getParameter("id")));

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/logradouro/lista.jsp");
		requestDispatcher.forward(request, response);
	}
}
