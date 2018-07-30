package br.com.sonner.estagio.servlet.bairro;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sonner.estagio.controller.BairroControllerImpl;
import br.com.sonner.estagio.controller.CidadeControllerImpl;
import br.com.sonner.estagio.controller.api.BairroController;
import br.com.sonner.estagio.controller.api.CidadeController;
import br.com.sonner.estagio.model.Bairro;
import br.com.sonner.estagio.model.Cidade;

/**
 * Servlet implementation class Atualiza
 */
public class Atualiza extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		CidadeController cidadeController = new CidadeControllerImpl();
		BairroController bairroController = new BairroControllerImpl();
		
		Cidade cidade = cidadeController.getOne(Long.valueOf(req.getParameter("cidade")));
		
		Bairro bairro = new Bairro();
		bairro.setId(Long.valueOf(req.getParameter("id")));
		bairro.setNome(req.getParameter("nome"));
		bairro.setCidade(cidade);
		
		bairroController.update(bairro);
		
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/bairro/atualiza.jsp");
		requestDispatcher.forward(req, res);
		
	}

}