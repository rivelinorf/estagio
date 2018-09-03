package br.com.sonner.estagio.servlet.estado;

import br.com.sonner.estagio.controller.EstadoControllerImpl;
import br.com.sonner.estagio.vos.EstadoFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/pesquisa-estado")
public class Pesquisa extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        EstadoControllerImpl estadoController = new EstadoControllerImpl();

        EstadoFiltroVO vo = new EstadoFiltroVO();
        vo.setEstado(request.getParameter("estado"));
        vo.setAbv(request.getParameter("abv"));

        HttpSession session = request.getSession();

        if (vo.getEstado() == null && vo.getAbv() == null) {
            session.setAttribute("filtroEstado", null);
            session.setAttribute("listaEstado", null);

        } else {
            session.setAttribute("filtroEstado", vo);
            session.setAttribute("listaEstado", estadoController.filtrarLike(vo));
        }

        response.sendRedirect("/views/estado/lista.jsp");
    }
}
