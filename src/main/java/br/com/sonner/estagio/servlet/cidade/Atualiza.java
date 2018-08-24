package br.com.sonner.estagio.servlet.cidade;

import br.com.sonner.estagio.controller.CidadeControllerImpl;
import br.com.sonner.estagio.controller.EstadoControllerImpl;
import br.com.sonner.estagio.controller.api.CidadeController;
import br.com.sonner.estagio.controller.api.EstadoController;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.vos.CidadeFiltroVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/atualiza-cidade")
public class Atualiza extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CidadeControllerImpl cidadeController = new CidadeControllerImpl();
		CidadeFiltroVO vo = new CidadeFiltroVO();
		EstadoController estadoController = new EstadoControllerImpl();
		Cidade novaCidade = new Cidade();
		HttpSession session = request.getSession();

		novaCidade.setId(Long.valueOf(request.getParameter("id")));
		novaCidade.setNome(request.getParameter("cidade"));
		novaCidade.setCep(request.getParameter("cep"));
		novaCidade.setCod(request.getParameter("codigo"));
		novaCidade.setEstado(estadoController.getOne(Long.valueOf(request.getParameter("estado"))));

		List<String> erros = cidadeController.validation(novaCidade);

		if (erros.size() == 0) {
			vo.setNome("");
			vo.setCod("");
			vo.setCep("");
			vo.setEstado(null);
			vo.setId(null);

			vo.setNome(novaCidade.getNome());
			vo.setEstado(novaCidade.getEstado().getId());

			List<Cidade> verificacidade = cidadeController.filtrar(vo);

			vo.setCep(novaCidade.getCep());

			CidadeFiltroVO cidadeantiga = (CidadeFiltroVO) session.getAttribute("cidadeParaEditar");

			vo.setNome("");
			vo.setEstado(null);

			List<Cidade> verificacep = cidadeController.filtrar(vo);

			if (verificacidade.size() == 0 && verificacep.size() == 0) {

				cidadeController.update(novaCidade);

				vo.setNome("");
				vo.setCod("");
				vo.setCep("");
				vo.setEstado(null);

				session.setAttribute("listaCidade", cidadeController.filtrar(vo));
				session.setAttribute("success", "Cidade atualizada com sucesso");

				response.sendRedirect("/views/cidade/lista.jsp");

			} else {

				String existe = "";
				if (verificacidade.size() > 0) {

					existe = "Cidade já cadastrada!";
				} else {
					existe = "CEP já utilizado";
				}
				session.setAttribute("errors", existe);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/cidade/atualiza.jsp");
				requestDispatcher.forward(request, response);
			}

		} else {
			session.setAttribute("errors", erros);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/cidade/atualiza.jsp");
			requestDispatcher.forward(request, response);
		}

	}
}
