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

@WebServlet("/bairro-deleta")
public class Deleta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		BairroControllerImpl bairroController = new BairroControllerImpl();
		BairroFiltroVO vo = new BairroFiltroVO();
		
		bairroController.delete(Long.valueOf(req.getParameter("id")));
		
		vo.setCidade(null);
		vo.setNome("");
		
		HttpSession session = req.getSession();
		session.setAttribute("listaBairro", bairroController.filtrar(vo));

		res.sendRedirect("/views/bairro/lista.jsp");
	}

}
