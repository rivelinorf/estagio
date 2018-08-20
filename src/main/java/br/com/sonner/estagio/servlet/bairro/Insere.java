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
import br.com.sonner.estagio.vos.CidadeFiltroVO;

/**
 * Servlet implementation class Insere
 */

@WebServlet("/insere-bairro")
public class Insere extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CidadeControllerImpl cidadeController = new CidadeControllerImpl();
        CidadeFiltroVO vo = new CidadeFiltroVO();
        
        if (request.getParameter("estado") != "") {
            vo.setEstado(Long.valueOf(request.getParameter("estado")));
    		vo.setNome("");
    		vo.setSigla("");
    		vo.setCep("");
        }

        HttpSession session = request.getSession();
        session.setAttribute("filtroCidade", vo);
        session.setAttribute("listaCidade", cidadeController.filtrar(vo));

        response.sendRedirect("/views/bairro/insere.jsp");
    }

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
			session.setAttribute("success", "Bairro inserido com sucesso");

			res.sendRedirect("/views/bairro/lista.jsp");
		} else {
			session.setAttribute("errors", erros);
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/bairro/insere.jsp");
			requestDispatcher.forward(req, res);
		}

	}
}
