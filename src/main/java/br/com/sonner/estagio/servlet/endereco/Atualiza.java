package br.com.sonner.estagio.servlet.endereco;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sonner.estagio.controller.BairroControllerImpl;
import br.com.sonner.estagio.controller.EnderecoControllerImpl;
import br.com.sonner.estagio.controller.LogradouroControllerImpl;
import br.com.sonner.estagio.model.Bairro;
import br.com.sonner.estagio.model.Endereco;
import br.com.sonner.estagio.model.Logradouro;
import br.com.sonner.estagio.vos.EnderecoFiltroVO;

/**
 * Servlet implementation class Atualiza
 */
@WebServlet("/atualiza-endereco")
public class Atualiza extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		LogradouroControllerImpl logradouroController = new LogradouroControllerImpl();
		BairroControllerImpl bairroController = new BairroControllerImpl();
		EnderecoControllerImpl enderecoController = new EnderecoControllerImpl();
		Endereco endereco = new Endereco();
		EnderecoFiltroVO vo = new EnderecoFiltroVO();
		HttpSession session = req.getSession();

		String cep = req.getParameter("cep");
		String complemento = req.getParameter("complemento");
		Integer numero = null;
		Logradouro logradouro = null;
		Bairro bairro = null;

		if (req.getParameter("numero") != "") {
			numero = Integer.parseInt(req.getParameter("numero"));
		}

		if (req.getParameter("logradouro") != "") {
			logradouro = logradouroController.getOne(Long.valueOf(req.getParameter("logradouro")));
		}

		if (req.getParameter("bairro") != "") {
			bairro = bairroController.getOne(Long.valueOf(req.getParameter("bairro")));
		}

		endereco.setId(Long.valueOf(req.getParameter("id")));
		endereco.setNumero(numero);
		endereco.setCep(req.getParameter("cep"));
		endereco.setComplemento(req.getParameter("complemento"));
		endereco.setBairro(bairro);
		endereco.setLogradouro(logradouro);

		List<String> erros = enderecoController.validation(endereco);

		if (erros.size() == 0) {

			enderecoController.update(endereco);

			vo.setNumero(null);
			vo.setCep("");
			vo.setComplemento("");
			vo.setBairro(null);
			vo.setLogradouro(null);

			session.setAttribute("listaEndereco", enderecoController.filtrar(vo));

			res.sendRedirect("/views/endereco/lista.jsp");

		}

		else {
			session.setAttribute("errors", erros);
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/endereco/atualiza.jsp");
			requestDispatcher.forward(req, res);
		}

	}

}