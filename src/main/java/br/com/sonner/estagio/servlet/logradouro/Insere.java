
package br.com.sonner.estagio.servlet.logradouro;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sonner.estagio.controller.CidadeControllerImpl;
import br.com.sonner.estagio.controller.LogradouroControllerImpl;
import br.com.sonner.estagio.controller.TipoLogradouroControllerImpl;
import br.com.sonner.estagio.controller.api.CidadeController;
import br.com.sonner.estagio.controller.api.LogradouroController;
import br.com.sonner.estagio.controller.api.TipoLogradouroController;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.model.Logradouro;
import br.com.sonner.estagio.model.TipoLogradouro;
import br.com.sonner.estagio.vos.LogradouroFiltroVO;

@WebServlet("/insere-logradouro")
public class Insere extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws  IOException {
		LogradouroController logradouroController = new LogradouroControllerImpl();
		TipoLogradouroController tipoLogradouroController = new TipoLogradouroControllerImpl();
		CidadeController cidadeController = new CidadeControllerImpl();

		LogradouroFiltroVO vo = new LogradouroFiltroVO();
		HttpSession session = req.getSession();

		Cidade cidade = cidadeController.getOne(Long.valueOf(req.getParameter("cidade")));
		TipoLogradouro tipoLogradouro = TipoLogradouro.getOne(Long.valueOf(req.getParameter("tipologradouro")));

		logradouroController.save(new Logradouro(req.getParameter("")));

		CidadeControllerImpl cidadeController = new CidadeControllerImpl();
		EstadoController estadoController = new EstadoControllerImpl();
		CidadeFiltroVO vo = new CidadeFiltroVO();
		HttpSession session = req.getSession();

		Estado estado = estadoController.getOne(Long.valueOf(req.getParameter("estado")));
		cidadeController.save(new Cidade(req.getParameter("nome"), req.getParameter("sigla"), req.getParameter("cep"), estado));

		vo.setNome("");
		vo.setSigla("");
		vo.setCep("");
		vo.setEstado(null);
		session.setAttribute("listaCidade", cidadeController.filtrar(vo));

		res.sendRedirect("/views/cidade/lista.jsp");
	}

}
