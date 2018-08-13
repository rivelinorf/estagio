package br.com.sonner.estagio.servlet.logradouro;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sonner.estagio.controller.BairroControllerImpl;
import br.com.sonner.estagio.controller.CidadeControllerImpl;
import br.com.sonner.estagio.controller.LogradouroControllerImpl;
import br.com.sonner.estagio.controller.TipoLogradouroControllerImpl;
import br.com.sonner.estagio.controller.api.CidadeController;
import br.com.sonner.estagio.controller.api.LogradouroController;
import br.com.sonner.estagio.controller.api.TipoLogradouroController;
import br.com.sonner.estagio.model.Bairro;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.model.Logradouro;
import br.com.sonner.estagio.model.TipoLogradouro;
import br.com.sonner.estagio.vos.LogradouroFiltroVO;

@WebServlet("/atualiza-logradouro")
public class Atualiza extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {

		TipoLogradouroController tipoLogradouroController = new TipoLogradouroControllerImpl();
		CidadeController cidadeController= new CidadeControllerImpl();
		LogradouroControllerImpl logradouroController = new LogradouroControllerImpl();
		LogradouroFiltroVO vo = new LogradouroFiltroVO();
		Logradouro novoLogradouro= new Logradouro();


		novoLogradouro.setId((Long.valueOf(request.getParameter("id"))));
		novoLogradouro.setNome(request.getParameter("logradouro"));
		novoLogradouro.setCidade(cidadeController.getOne(Long.valueOf(request.getParameter("cidade"))));
		novoLogradouro.setTipologradouro(tipoLogradouroController.getOne(Long.valueOf(request.getParameter("tipologradouro"))));

		logradouroController.update(novoLogradouro);

		vo.setNome("");
		vo.setCidade(null);
		vo.setTipologradouro(null);


		HttpSession session = request.getSession();
		((HttpSession) session).setAttribute("listaLogradouro", logradouroController.filtrar(vo));

		response.sendRedirect("/views/logradouro/lista.jsp");
	}
}
