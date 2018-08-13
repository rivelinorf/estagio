package br.com.sonner.estagio.servlet.bairro;

import java.io.IOException;

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

@WebServlet("/bairro-insere")
public class Insere extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws javax.servlet.ServletException, IOException {

		/*Bairro bairro = new Bairro();
		CidadeControllerImpl cidadeController = new CidadeControllerImpl();
		BairroControllerImpl bairroController = new BairroControllerImpl();
		
		String nome = req.getParameter("nome");
		Long cidadeID = Long.valueOf(req.getParameter("cidadeID"));
		
		Cidade cidade = cidadeController.getOne(cidadeID);
		
		bairro.setCidade(cidade);
		bairro.setNome(nome);

		bairroController.save(bairro);

		HttpSession session = req.getSession();
		BairroFiltroVO vo = new BairroFiltroVO();
		
		vo.setCidade(null);
		vo.setNome("");
		session.setAttribute("lista", bairroController.filtrar(vo));
		
		res.sendRedirect("/views/bairro/lista.jsp");*/
	}
}
