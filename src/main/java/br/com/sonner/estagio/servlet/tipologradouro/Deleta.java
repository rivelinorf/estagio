package br.com.sonner.estagio.servlet.tipologradouro;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import br.com.sonner.estagio.controller.TipoLogradouroControllerImpl;
import br.com.sonner.estagio.vos.TipologradouroFiltroVO;

@SuppressWarnings("serial")
@WebServlet("/deleta-tipologradouro")
public class Deleta extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		TipoLogradouroControllerImpl tipologradouroController = new TipoLogradouroControllerImpl();
		tipologradouroController.delete(Long.valueOf(request.getParameter("id")));

		HttpSession session = request.getSession();
		TipologradouroFiltroVO vo = new TipologradouroFiltroVO();

		vo.setNome("");

		session.setAttribute("listaTipologradouro", tipologradouroController.filtrar(vo));

		response.sendRedirect("/views/tipologradouro/lista.jsp");

	}
}
