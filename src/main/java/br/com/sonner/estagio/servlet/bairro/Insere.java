package br.com.sonner.estagio.servlet.bairro;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sonner.estagio.controller.BairroControllerImpl;
import br.com.sonner.estagio.controller.CidadeControllerImpl;
import br.com.sonner.estagio.model.Bairro;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.vos.BairroFiltroVO;

/**
 * Servlet implementation class Insere
 */

@WebServlet("/insere-bairro")
public class Insere extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws javax.servlet.ServletException, IOException {

		BairroControllerImpl bairroController = new BairroControllerImpl();
		CidadeControllerImpl cidadeController = new CidadeControllerImpl();
		BairroFiltroVO vo = new BairroFiltroVO();
		HttpSession session = req.getSession();
		Cidade cidade = null;

		if (req.getParameter("cidadeID") != "") {
			cidade = cidadeController.getOne(Long.valueOf(req.getParameter("cidadeID")));
		}
		String nome = req.getParameter("nome");
		Bairro bairro = new Bairro(nome, cidade);
		List<String> erros = bairroController.validation(bairro);

		if (erros.size() == 0) {
			bairroController.save(bairro);

			vo.setNome("");
			vo.setCidade(null);
			session.setAttribute("listaBairro", bairroController.filtrar(vo));

			res.sendRedirect("/views/bairro/lista.jsp");
		} else {
			session.setAttribute("errors", erros);
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/bairro/insere.jsp");
			requestDispatcher.forward(req, res);
		}

	}
}
