package br.com.sonner.estagio.servlet.estado;

import br.com.sonner.estagio.controller.CidadeControllerImpl;
import br.com.sonner.estagio.controller.EstadoControllerImpl;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.CidadeFiltroVO;
import br.com.sonner.estagio.vos.EstadoFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/estado-deleta")
public class Deleta extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        EstadoControllerImpl estadoController = new EstadoControllerImpl();
        CidadeFiltroVO cidadeFiltroVO = new CidadeFiltroVO();
        HttpSession session = request.getSession();
        EstadoFiltroVO estadoFiltroVO = (EstadoFiltroVO) session.getAttribute("filtroEstado");

        cidadeFiltroVO.setNome("");
        cidadeFiltroVO.setCep("");
        cidadeFiltroVO.setCod("");
        cidadeFiltroVO.setEstado(Long.valueOf(request.getParameter("id")));

        try {
            estadoController.delete(Long.valueOf(request.getParameter("id")));
        } catch (CustomException e) {
            session.setAttribute("errors", e.getMessage());
        }

        if (estadoFiltroVO == null) {
            estadoFiltroVO = new EstadoFiltroVO();
        }

        session.setAttribute("listaEstado", estadoController.filtrar(estadoFiltroVO));
        response.sendRedirect("/views/estado/lista.jsp");
    }
}
