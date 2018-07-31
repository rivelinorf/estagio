package br.com.sonner.estagio.servlet.logradouro;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sonner.estagio.controller.CidadeControllerImpl;
import br.com.sonner.estagio.controller.LogradouroControllerImpl;
import br.com.sonner.estagio.controller.TipoLogradouroControllerImpl;
import br.com.sonner.estagio.controller.api.CidadeController;
import br.com.sonner.estagio.controller.api.LogradouroController;
import br.com.sonner.estagio.controller.api.TipoLogradouroController;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.model.Logradouro;
import br.com.sonner.estagio.model.TipoLogradouro;

@WebServlet("/logradouro-atualiza")
public class Atualiza extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		CidadeController cidadeController = new CidadeControllerImpl();
		LogradouroController logradouroController = new LogradouroControllerImpl();
		TipoLogradouroController tipoLogradouroController = new TipoLogradouroControllerImpl();

		TipoLogradouro tipologradouro = tipoLogradouroController.getOne(Long.valueOf(request.getParameter("tipologradouro")));
		Cidade cidade = cidadeController.getOne(Long.valueOf(request.getParameter("cidade")));

		Logradouro logradouro = new Logradouro();
		logradouro.setId(Long.valueOf(request.getParameter("id")));
		logradouro.setNome(request.getParameter("nome"));
		logradouro.setCidade(cidade);
		logradouro.setTipologradouro(tipologradouro);

		logradouroController.update(logradouro);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/logradouro/lista.jsp");
		requestDispatcher.forward(request, response);
	}
}
