package br.com.sonner.estagio.servlet.bairro;

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
import br.com.sonner.estagio.controller.CidadeControllerImpl;
import br.com.sonner.estagio.model.Bairro;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.vos.BairroFiltroVO;

/**
 * Servlet implementation class Atualiza
 */
@WebServlet("/atualiza-bairro")
public class Atualiza extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		CidadeControllerImpl cidadeController = new CidadeControllerImpl();
		BairroControllerImpl bairroController = new BairroControllerImpl();
		Bairro bairro = new Bairro();
		BairroFiltroVO vo = new BairroFiltroVO();
		HttpSession session = req.getSession();

		String nome = req.getParameter("nome");
		Cidade cidade = null;

		if (req.getParameter("cidade") != "") {
			cidade = cidadeController.getOne(Long.valueOf(req.getParameter("cidade")));
		}

		bairro.setId(Long.valueOf(req.getParameter("id")));
		bairro.setNome(nome);
		bairro.setCidade(cidade);

		List<String> erros = bairroController.validation(bairro);

		if (erros.size() == 0) {

			bairroController.update(bairro);

			vo.setCidade(null);
			vo.setNome("");

			session.setAttribute("listaBairro", bairroController.filtrar(vo));
			session.setAttribute("success", "Bairro atualizado com sucesso");

			res.sendRedirect("/views/bairro/lista.jsp");
		}
		
		else {
			session.setAttribute("errors", erros);
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/bairro/atualiza.jsp");
			requestDispatcher.forward(req, res);
		}

	}

}
