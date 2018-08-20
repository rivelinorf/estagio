package br.com.sonner.estagio.servlet.bairro;

import java.io.IOException;

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

@WebServlet("/bairro/preenche-vo")
public class PreencheVo extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	BairroControllerImpl bairroController = new BairroControllerImpl();
    	BairroFiltroVO vo = new BairroFiltroVO();
		CidadeControllerImpl cidadeController = new CidadeControllerImpl();
		CidadeFiltroVO cidadevo = new CidadeFiltroVO();
		
    	
    	Bairro bairro = bairroController.getOne(Long.valueOf(request.getParameter("id")));
		Cidade cidade = cidadeController.getOne(bairro.getCidade().getId());
		
        cidadevo.setId(cidade.getId());
        cidadevo.setNome(cidade.getNome());
        cidadevo.setCep(cidade.getCep());
        cidadevo.setSigla(cidade.getCod());
        cidadevo.setEstado(cidade.getEstado().getId());

    	vo.setId(bairro.getId());
    	vo.setNome(bairro.getNome());
    	vo.setCidade(bairro.getCidade().getId());
    	
    	HttpSession session = request.getSession();
    	session.setAttribute("listaCidade", cidadeController.filtrar(cidadevo));
    	session.setAttribute("bairroParaEditar", vo);
    	session.setAttribute("filtroCidade", cidadevo);
    	
    	response.sendRedirect("/views/bairro/atualiza.jsp");
    }
    
}
