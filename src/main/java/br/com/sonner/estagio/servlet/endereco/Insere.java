package br.com.sonner.estagio.servlet.endereco;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sonner.estagio.controller.BairroControllerImpl;
import br.com.sonner.estagio.controller.EnderecoControllerImpl;
import br.com.sonner.estagio.controller.LogradouroControllerImpl;
import br.com.sonner.estagio.controller.api.BairroController;
import br.com.sonner.estagio.controller.api.EnderecoController;
import br.com.sonner.estagio.controller.api.LogradouroController;
import br.com.sonner.estagio.model.Bairro;
import br.com.sonner.estagio.model.Endereco;
import br.com.sonner.estagio.model.Logradouro;

/**
 * Servlet implementation class Insere
 */
@WebServlet("/endereco-insere")
public class Insere extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		LogradouroController logradouroController = new LogradouroControllerImpl();
		BairroController bairroController = new BairroControllerImpl();
		EnderecoController enderecoController = new EnderecoControllerImpl();

		Logradouro logradouro = logradouroController.getOne(Long.valueOf(req.getParameter("logradouro")));
		Bairro bairro = bairroController.getOne(Long.valueOf(req.getParameter("bairro")));

		enderecoController.save(new Endereco(Integer.parseInt(req.getParameter("numero")), req.getParameter("cep"), req.getParameter("complemento"),
				bairro, logradouro));

		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/endereco/lista.jsp");
		requestDispatcher.forward(req, res);;
	}

}
