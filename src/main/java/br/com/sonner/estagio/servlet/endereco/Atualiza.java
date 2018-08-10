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
 * Servlet implementation class Atualiza
 */
@WebServlet("/endereco-atualiza")
public class Atualiza extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Endereco endereco = new EnderecoControllerImpl().getOne(Long.valueOf(req.getParameter("id")));
		req.setAttribute("endereco", endereco);
		RequestDispatcher resquestDispatcher = req.getRequestDispatcher("views/endereco/atualiza.jsp");
		resquestDispatcher.forward(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		LogradouroController logradouroController = new LogradouroControllerImpl();
		BairroController bairroController = new BairroControllerImpl();
		EnderecoController enderecoController = new EnderecoControllerImpl();
		Endereco endereco = new Endereco();

		Logradouro logradouro = logradouroController.getOne(Long.valueOf(req.getParameter("logradouro")));
		Bairro bairro = bairroController.getOne(Long.valueOf(req.getParameter("bairro")));

		endereco.setId(Long.valueOf(req.getParameter("id")));
		endereco.setNumero(Integer.parseInt(req.getParameter("numero")));
		endereco.setCep(req.getParameter("cep"));
		endereco.setComplemento(req.getParameter("complemento"));
		endereco.setBairro(bairro);
		endereco.setLogradouro(logradouro);

		enderecoController.update(endereco);

		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/endereco/lista.jsp");
		requestDispatcher.forward(req, res);
	}

}