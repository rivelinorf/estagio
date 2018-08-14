package br.com.sonner.estagio.servlet.bairro;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sonner.estagio.controller.BairroControllerImpl;
import br.com.sonner.estagio.vos.BairroFiltroVO;

@WebServlet("/pesquisa-bairro")
public class Pesquisa extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BairroControllerImpl bairroController = new BairroControllerImpl();
        BairroFiltroVO vo = new BairroFiltroVO();

        vo.setNome(request.getParameter("nome"));
		
        if(request.getParameter("cidadeID") != "") {
            vo.setCidade(Long.valueOf(request.getParameter("cidadeID")));
        }

		HttpSession session = request.getSession();
        session.setAttribute("filtroBairro", vo);
        session.setAttribute("listaBairro", bairroController.filtrar(vo));

        response.sendRedirect("/views/bairro/lista.jsp");

	}

}
