package br.com.sonner.estagio.servlet.tipologradouro;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sonner.estagio.controller.BairroControllerImpl;
import br.com.sonner.estagio.controller.TipoLogradouroControllerImpl;

@WebServlet("/deleta-tipologradouro")
public class Deleta extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		new TipoLogradouroControllerImpl().delete(Long.valueOf(request.getParameter("id")));
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/tipologradouro/lista.jsp");
		requestDispatcher.forward(request, response);
	}
}
