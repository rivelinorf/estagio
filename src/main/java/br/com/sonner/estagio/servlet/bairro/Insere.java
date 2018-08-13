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
		
		System.out.println("entrou");

        BairroControllerImpl bairroController = new BairroControllerImpl();
        CidadeControllerImpl cidadeController = new CidadeControllerImpl();
        BairroFiltroVO vo = new BairroFiltroVO();
        HttpSession session = req.getSession();

        Cidade cidade = cidadeController.getOne(Long.valueOf(req.getParameter("cidadeID")));
        bairroController.save(new Bairro(req.getParameter("nome"), cidade));

        vo.setNome("");
        vo.setCidade(null);
        session.setAttribute("listaBairro", bairroController.filtrar(vo));

        res.sendRedirect("/views/bairro/lista.jsp");
	}
}
