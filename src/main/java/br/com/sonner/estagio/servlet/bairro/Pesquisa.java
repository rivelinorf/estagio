package br.com.sonner.estagio.servlet.bairro;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sonner.estagio.controller.BairroControllerImpl;

@WebServlet("/pesquisa-bairro")
public class Pesquisa extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BairroControllerImpl bairroController = new BairroControllerImpl();
		bairroController.setBairrosPesquisados(request.getParameter("nome"), Long.valueOf(request.getParameter("cidadeID")));
	}

}
