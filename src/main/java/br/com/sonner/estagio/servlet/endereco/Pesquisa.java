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

@WebServlet("/pesquisa-endereco")
public class Pesquisa extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EnderecoControllerImpl enderecoController = new EnderecoControllerImpl();
        EnderecoFiltroVO vo = new EnderecoFiltroVO();

        vo.setCep(request.getParameter("cep"));
        vo.setComplemento(request.getParameter("complemento"));
		
        if(request.getParameter("bairro") != "") {
            vo.setBairro(Long.valueOf(request.getParameter("bairro")));
        }
        
        if(request.getParameter("logradouro") != "") {
            vo.setLogradouro(Long.valueOf(request.getParameter("logradouro")));
        }
        
        if(request.getParameter("numero") != "") {
            vo.setNumero(Integer.parseInt(request.getParameter("numero")));
        }

		HttpSession session = request.getSession();
        session.setAttribute("filtroEndereco", vo);
        session.setAttribute("listaEndereco", enderecoController.filtrar(vo));

        response.sendRedirect("/views/endereco/lista.jsp");

	}

}

