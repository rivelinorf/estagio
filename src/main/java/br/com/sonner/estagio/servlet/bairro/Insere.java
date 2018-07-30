package br.com.sonner.estagio.servlet.bairro;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sonner.estagio.controller.BairroControllerImpl;
import br.com.sonner.estagio.controller.EstadoControllerImpl;
import br.com.sonner.estagio.model.Bairro;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.model.Estado;

/**
 * Servlet implementation class Insere
 */

@WebServlet("/bairro-insere")
public class Insere extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws javax.servlet.ServletException, IOException {

		PrintWriter out = response.getWriter();

		Estado estado = new EstadoControllerImpl().getOne(2);
		Cidade cidade = new Cidade("Uberlandia", "udia", "5656", estado);
		cidade.setId(1);
		String nome = request.getParameter("nome");

		Bairro bairro = new Bairro(nome, cidade);

		BairroControllerImpl bairroC = new BairroControllerImpl();
		bairroC.save(bairro);

	}
}
