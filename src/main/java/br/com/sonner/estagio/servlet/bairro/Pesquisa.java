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
import br.com.sonner.estagio.controller.api.CidadeController;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.vos.BairroFiltroVO;

@WebServlet("/pesquisa-bairro")
public class Pesquisa extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CidadeController cidadeController = new CidadeControllerImpl();
		BairroControllerImpl bairroController = new BairroControllerImpl();
		
		BairroFiltroVO vo = new BairroFiltroVO();
		
		String nome = request.getParameter("nome");
		Long cidade = Long.valueOf(request.getParameter("cidadeID"));
		
		vo.setNome(nome);
		vo.setCidade(cidade);
		
		HttpSession session = request.getSession();
		session.setAttribute("filtro", vo);
		session.setAttribute("lista", bairroController.filtrar(vo));
		
		response.sendRedirect("/views/bairro/lista.jsp");
		
	}

}
