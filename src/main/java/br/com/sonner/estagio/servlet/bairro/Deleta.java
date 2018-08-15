package br.com.sonner.estagio.servlet.bairro;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sonner.estagio.controller.BairroControllerImpl;
import br.com.sonner.estagio.controller.EnderecoControllerImpl;
import br.com.sonner.estagio.vos.BairroFiltroVO;
import br.com.sonner.estagio.vos.EnderecoFiltroVO;

@WebServlet("/bairro-deleta")
public class Deleta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		BairroControllerImpl bairroController = new BairroControllerImpl();
		EnderecoControllerImpl enderecoController = new EnderecoControllerImpl();
		EnderecoFiltroVO enderecoVO = new EnderecoFiltroVO();
		BairroFiltroVO vo = new BairroFiltroVO();
		HttpSession session = req.getSession();
		
		enderecoVO.setBairro(Long.valueOf(req.getParameter("id")));
		enderecoVO.setCep("");
		enderecoVO.setComplemento("");
		enderecoVO.setLogradouro(null);
		enderecoVO.setNumero(null);
		
		if(enderecoController.filtrar(enderecoVO).size() > 0) {
			session.setAttribute("errors", "Impossível deletar! Bairro possui relacionamento");
		} else {
			bairroController.delete(Long.valueOf(req.getParameter("id")));
			session.setAttribute("errors", "");
		}
		
	
		vo.setCidade(null);
		vo.setNome("");
		
		session.setAttribute("listaBairro", bairroController.filtrar(vo));

		res.sendRedirect("/views/bairro/lista.jsp");
	}

}
