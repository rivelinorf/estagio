package br.com.sonner.estagio.servlet.bairro;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sonner.estagio.controller.BairroControllerImpl;
import br.com.sonner.estagio.controller.CidadeControllerImpl;
import br.com.sonner.estagio.controller.api.BairroController;
import br.com.sonner.estagio.controller.api.CidadeController;
import br.com.sonner.estagio.model.Bairro;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.vos.BairroFiltroVO;

/**
 * Servlet implementation class Atualiza
 */
@WebServlet("/bairro-atualiza")
public class Atualiza extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		/*CidadeController cidadeController = new CidadeControllerImpl();
		BairroController bairroController = new BairroControllerImpl();
		Bairro bairro = new Bairro();

		Cidade cidade = cidadeController.getOne(Long.valueOf(req.getParameter("cidadeID")));

		bairro.setId(Long.valueOf(req.getParameter("id")));
		bairro.setNome(req.getParameter("nome"));
		bairro.setCidade(cidade);

		bairroController.update(bairro);

		HttpSession session = req.getSession();
		BairroFiltroVO vo = new BairroFiltroVO();
		
		vo.setCidade(null);
		vo.setNome("");
		session.setAttribute("lista", bairroController.filtrar(vo));
		
		res.sendRedirect("/views/bairro/lista.jsp");*/

	}

}
