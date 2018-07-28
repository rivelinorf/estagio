package br.com.sonner.estagio.servlet.tipologradouro;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.spi.protocol.RequestDispatcherDefault;

import br.com.sonner.estagio.controller.TipoLogradouroControllerImpl;
import br.com.sonner.estagio.controller.api.TipoLogradouroController;
import br.com.sonner.estagio.model.TipoLogradouro;

@WebServlet("/tipologradouro-atualiza")
public class Atualiza extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TipoLogradouroController tipoLogradouroController = new TipoLogradouroControllerImpl();
		TipoLogradouro tipoLogradouro = new TipoLogradouro();

		tipoLogradouro.setId(Long.valueOf(request.getParameter("id")));
		tipoLogradouro.setNome(request.getParameter("nome"));

		tipoLogradouroController.update(tipoLogradouro);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/tipologradouro/atualiza.jsp");
		requestDispatcher.forward(request, response);

	}
}
