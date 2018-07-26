package br.com.sonner.estagio.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sonner.estagio.controller.EstadoControllerImpl;
import br.com.sonner.estagio.controller.TipoLogradouroControllerImpl;
import br.com.sonner.estagio.model.Estado;
import br.com.sonner.estagio.model.TipoLogradouro;

@WebServlet("/tipoLogradouro")
public class TipoLogradouroServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		TipoLogradouro novoTipoLogradouro = new TipoLogradouro(req.getParameter("nome"));
		new TipoLogradouroControllerImpl().save(novoTipoLogradouro);
	}
}
