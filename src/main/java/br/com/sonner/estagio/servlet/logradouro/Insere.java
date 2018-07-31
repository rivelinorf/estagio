
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

@WebServlet("/logradouro-insere")
public class Insere extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		LogradouroController logradouroController = new LogradouroControllerImpl();
		CidadeController cidadeController = new CidadeControllerImpl();
		TipoLogradouroController tipoLogradouroController = new TipoLogradouroControllerImpl();
		
		TipoLogradouro tipologradouro = tipoLogradouroController.getOne(Long.valueOf(req.getParameter("tipoLogradouro")));
		
		
		Cidade cidade = cidadeController.getOne(Long.valueOf(req.getParameter("cidade")));

		logradouroController.save(new Logradouro(req.getParameter("nome"), tipologradouro, cidade));

		RequestDispatcher rd = req.getRequestDispatcher("/logradouro/lista.jsp");
		rd.forward(req, res);
	}

}
