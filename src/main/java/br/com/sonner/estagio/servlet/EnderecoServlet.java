package br.com.sonner.estagio.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sonner.estagio.controller.EnderecoControllerImpl;
import br.com.sonner.estagio.model.Endereco;

@WebServlet("/endereco")

public class EnderecoServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		Endereco e = new Endereco(Integer.parseInt(req.getParameter("numero")),req.getParameter("cep"),null,null);
		new EnderecoControllerImpl().save(e);
	}
}
