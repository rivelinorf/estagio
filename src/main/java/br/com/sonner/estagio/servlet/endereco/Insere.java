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
 * Servlet implementation class Insere
 */
@WebServlet("/insere-endereco")
public class Insere extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		LogradouroControllerImpl logradouroController = new LogradouroControllerImpl();
		BairroControllerImpl bairroController = new BairroControllerImpl();
		EnderecoControllerImpl enderecoController = new EnderecoControllerImpl();
		Endereco endereco = new Endereco();
		EnderecoFiltroVO vo = new EnderecoFiltroVO();
		HttpSession session = req.getSession();

		Logradouro logradouro = logradouroController.getOne(Long.valueOf(req.getParameter("logradouro")));
		Bairro bairro = bairroController.getOne(Long.valueOf(req.getParameter("bairro")));

		List<String> erros = enderecoController.validation(endereco);

		if (erros.size() == 0) {

			enderecoController.save(new Endereco(Integer.parseInt(req.getParameter("numero")), req.getParameter("cep"),
					req.getParameter("complemento"), bairro, logradouro));

			vo.setNumero(null);
			vo.setCep("");
			vo.setComplemento("");
			vo.setBairro(null);
			vo.setLogradouro(null);

			session.setAttribute("listaEndereco", enderecoController.filtrar(vo));

			res.sendRedirect("/views/endereco/lista.jsp");
		} else {
			session.setAttribute("errors", erros);
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/endereco/insere.jsp");
			requestDispatcher.forward(req, res);
		}

	}

}
