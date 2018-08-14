package br.com.sonner.estagio.servlet.endereco;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sonner.estagio.controller.EnderecoControllerImpl;
import br.com.sonner.estagio.model.Endereco;
import br.com.sonner.estagio.vos.EnderecoFiltroVO;

@WebServlet("/endereco/preenche-vo")
public class PreencheVo extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EnderecoControllerImpl enderecoController = new EnderecoControllerImpl();
		EnderecoFiltroVO vo = new EnderecoFiltroVO();
		
		Endereco endereco = enderecoController.getOne(Long.valueOf(request.getParameter("id")));
		vo.setId(endereco.getId());
		vo.setNumero(endereco.getNumero());
		vo.setCep(endereco.getCep());
		vo.setComplemento(endereco.getComplemento());
		vo.setBairro(endereco.getBairro().getId());
		vo.setLogradouro(endereco.getLogradouro().getId());
		
        HttpSession session = request.getSession();
        session.setAttribute("enderecoParaEditar", vo);

        response.sendRedirect("/views/endereco/atualiza.jsp");
	}

}
