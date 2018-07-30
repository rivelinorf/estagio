package br.com.sonner.estagio.servlet.tipologradouro;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sonner.estagio.controller.TipoLogradouroControllerImpl;
import br.com.sonner.estagio.controller.api.TipoLogradouroController;
import br.com.sonner.estagio.model.TipoLogradouro;

@WebServlet("/tipologradouro-insere")
public class Insere extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		TipoLogradouroController tipoLogradouroController = new TipoLogradouroControllerImpl();
		tipoLogradouroController.save(new TipoLogradouro("nome"));

		RequestDispatcher rd = req.getRequestDispatcher("/tipologradouro/insere.jsp");

		rd.forward(req, res);

	}
}