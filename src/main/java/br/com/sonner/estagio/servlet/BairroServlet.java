package br.com.sonner.estagio.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sonner.estagio.controller.BairroControllerImpl;
import br.com.sonner.estagio.model.Bairro;

@WebServlet("/bairro")

public class BairroServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		Bairro b = new Bairro(req.getParameter("nome"), null);
		new BairroControllerImpl().save(b);
	}
}