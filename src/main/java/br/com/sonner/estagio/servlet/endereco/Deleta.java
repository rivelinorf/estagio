package br.com.sonner.estagio.servlet.endereco;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sonner.estagio.controller.EnderecoControllerImpl;
import br.com.sonner.estagio.vos.EnderecoFiltroVO;

@WebServlet("/endereco-deleta")
public class Deleta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		EnderecoControllerImpl enderecoController = new EnderecoControllerImpl();
		EnderecoFiltroVO vo = new EnderecoFiltroVO();
		HttpSession session = req.getSession();

		enderecoController.delete(Long.valueOf(req.getParameter("id")));
		session.setAttribute("success", "Endereço deletado com sucesso");

		vo.setNumero(null);
		vo.setCep("");
		vo.setComplemento("");
		vo.setLogradouro(null);
		vo.setBairro(null);

		session.setAttribute("listaEndereco", enderecoController.filtrar(vo));

		res.sendRedirect("/views/endereco/lista.jsp");
	}

}