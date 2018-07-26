package br.com.sonner.estagio.servlet;

import br.com.sonner.estagio.controller.EstadoControllerImpl;
import br.com.sonner.estagio.model.Estado;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/estado")
public class EstadoServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		if (req.getParameter("nome") == null || req.getParameter("nome").length() == 0) {
			try {
				res.getWriter().append("DEU MUITO RUIM");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {

			Estado novoEstado = new Estado(req.getParameter("nome"), req.getParameter("abv"));
			new EstadoControllerImpl().save(novoEstado);
		}
	}
}