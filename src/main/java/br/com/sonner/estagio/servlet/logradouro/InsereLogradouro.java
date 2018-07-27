
package br.com.sonner.estagio.servlet.logradouro;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sonner.estagio.controller.CidadeControllerImpl;
import br.com.sonner.estagio.controller.EstadoControllerImpl;
import br.com.sonner.estagio.controller.LogradouroControllerImpl;
import br.com.sonner.estagio.controller.TipoLogradouroControllerImpl;
import br.com.sonner.estagio.controller.api.CidadeController;
import br.com.sonner.estagio.controller.api.LogradouroController;
import br.com.sonner.estagio.controller.api.TipoLogradouroController;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.model.Estado;
import br.com.sonner.estagio.model.Logradouro;
import br.com.sonner.estagio.model.TipoLogradouro;

@WebServlet("/logradouro-insere")

public class InsereLogradouro extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		CidadeController cidadeController = new CidadeControllerImpl();
		LogradouroController logradouroController = new LogradouroControllerImpl();
		TipoLogradouroController tipoLogradouroController = new TipoLogradouroControllerImpl();
		TipoLogradouro tipologradouro = tipoLogradouroController
				.getOne(Long.valueOf(req.getParameter("tipologradouro")));
		Cidade cidade = cidadeController.getOne(Long.valueOf(req.getParameter("cidade")));

		logradouroController.save(new Logradouro("nome", tipologradouro, cidade));

		RequestDispatcher rd = req.getRequestDispatcher("/logradouro/lista.jsp");

		rd.forward(req, res);
	}

}
