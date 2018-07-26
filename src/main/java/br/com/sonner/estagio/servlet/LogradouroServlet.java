package br.com.sonner.estagio.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sonner.estagio.controller.LogradouroControllerImpl;
import br.com.sonner.estagio.model.Logradouro;

@WebServlet("/Logradouro")

public class LogradouroServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		Logradouro novoLogradouro = new Logradouro(req.getParameter("nome"), null);
		new LogradouroControllerImpl().save(novoLogradouro);
	}
	
}
