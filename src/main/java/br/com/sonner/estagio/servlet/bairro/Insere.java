package br.com.sonner.estagio.servlet.bairro;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sonner.estagio.controller.BairroControllerImpl;
import br.com.sonner.estagio.controller.CidadeControllerImpl;
import br.com.sonner.estagio.controller.EstadoControllerImpl;
import br.com.sonner.estagio.controller.api.BairroController;
import br.com.sonner.estagio.controller.api.CidadeController;
import br.com.sonner.estagio.model.Bairro;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.model.Estado;

/**
 * Servlet implementation class Insere
 */

@WebServlet("/bairro-insere")
public class Insere extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws javax.servlet.ServletException, IOException {

		CidadeController cidadeController = new CidadeControllerImpl();
		BairroController bairroController = new BairroControllerImpl();

		Cidade cidade = cidadeController.getOne(Long.valueOf(req.getParameter("cidadeID")));

		bairroController.save(new Bairro(req.getParameter("nome"), cidade));

		RequestDispatcher rd = req.getRequestDispatcher("/views/bairro/lista.jsp");
		rd.forward(req, res);

	}
}
