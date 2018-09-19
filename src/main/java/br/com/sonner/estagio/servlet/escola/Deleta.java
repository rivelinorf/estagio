package br.com.sonner.estagio.servlet.escola;

import br.com.sonner.estagio.controller.EscolaControllerImpl;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.EscolaFiltroVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/escola-deleta")
public class Deleta  extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EscolaControllerImpl escolaController = new EscolaControllerImpl();
        HttpSession session = request.getSession();
        EscolaFiltroVO escolaFiltroVO = (EscolaFiltroVO) session.getAttribute("filtroEscola");

        try {
            escolaController.delete(Long.valueOf(request.getParameter("id")));
            session.setAttribute("success", "Escola deletada com sucesso");
        } catch (CustomException e){
            session.setAttribute("errors", e.getMessage());
        }

        if (escolaFiltroVO == null) {
            escolaFiltroVO = new EscolaFiltroVO();
        }

        session.setAttribute("listaEscola", escolaController.filtrar(escolaFiltroVO));
        response.sendRedirect("/views/escola/lista.jsp");
    }
}
