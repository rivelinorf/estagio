package br.com.sonner.estagio.servlet.logradouro;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import br.com.sonner.estagio.controller.LogradouroControllerImpl;
import br.com.sonner.estagio.vos.LogradouroFiltroVO;

@WebServlet("/pesquisa-logradouro")
public class Pesquisa extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LogradouroControllerImpl logradouroController = new LogradouroControllerImpl();
		LogradouroFiltroVO vo = new  LogradouroFiltroVO();
		
		String nome = request.getParameter("nome");
		Long cidade = Long.valueOf(request.getParameter("cidadeID"));
		Long tipologradouro = Long.valueOf(request.getParameter("tipologradouroID"));
		
		vo.setCidade(cidade);
		vo.setNome(nome);
		vo.setTipologradouro(tipologradouro);
		
        HttpSession session = request.getSession();
        session.setAttribute("filtro", vo);
        session.setAttribute("lista", logradouroController.filtrar(vo));
        
        response.sendRedirect("/views/logradouro/lista.jsp");
	}
}
