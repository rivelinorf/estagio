package br.com.sonner.estagio.servlet.estado;

import br.com.sonner.estagio.controller.EstadoControllerImpl;
import br.com.sonner.estagio.model.Estado;
import br.com.sonner.estagio.vos.EstadoFiltroVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/atualiza-estado")
public class Atualiza extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EstadoControllerImpl estadoController = new EstadoControllerImpl();
		EstadoFiltroVO vo = new EstadoFiltroVO();
		HttpSession session = request.getSession();
		Estado estado = new Estado();
		if (request.getParameter("estado") != null && request.getParameter("estado") != "") {

			estado.setNome(request.getParameter("estado"));
		}
		if (request.getParameter("abv") != "" && request.getParameter("abv") != null) {

			estado.setAbv(request.getParameter("abv"));
		}
		estado.setId(Long.valueOf(request.getParameter("id")));

		List<String> erros = estadoController.validation(estado);

		if (erros.size() == 0) {

			vo.setEstado("");
			vo.setAbv("");
			vo.setId(null);

			vo.setEstado(estado.getNome());

			List<Estado> verificaestado = estadoController.filtrar(vo);

			vo.setEstado(null);
			vo.setAbv(estado.getAbv());

			List<Estado> verificaabv = estadoController.filtrar(vo);

			if (verificaestado.size() == 0 && verificaabv.size() == 0) {

				estadoController.update(estado);

				vo.setEstado("");
				vo.setAbv("");
				session.setAttribute("listaEstado", estadoController.filtrar(vo));
				session.setAttribute("success", "Estado atualizado com sucesso");

				response.sendRedirect("/views/estado/lista.jsp");
			}

			else {

				String existe = "";

				if (verificaestado.size() > 0) {
					existe = "Estado já cadastrado!";

				} else {
					existe = "Abreviação já cadastrada!";

				}
				session.setAttribute("errors", existe);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/estado/atualiza.jsp");
				requestDispatcher.forward(request, response);
			}
		} else {
			session.setAttribute("errors", erros);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/estado/atualiza.jsp");
			requestDispatcher.forward(request, response);
		}
	}
}
