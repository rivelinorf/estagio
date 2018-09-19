package br.com.sonner.estagio.servlet.turma;

import br.com.sonner.estagio.controller.TurmaControllerImpl;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.TurmaFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/deleta-turma")

public class Deleta extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TurmaControllerImpl turmaController = new TurmaControllerImpl();
        HttpSession session = request.getSession();

        TurmaFiltroVO turmaFiltroVO = (TurmaFiltroVO) session.getAttribute("filtroTurma");

        try {
            turmaController.delete(Long.valueOf(request.getParameter("id")));
            session.setAttribute("success", "Turma deletada com sucesso");

        } catch (CustomException e) {
            session.setAttribute("errors", e.getMessage());
        }

        if (turmaFiltroVO == null) {
            turmaFiltroVO = new TurmaFiltroVO();
        }

        session.setAttribute("listaTurma", turmaController.filtrar(turmaFiltroVO));
        response.sendRedirect("/views/turma/lista.jsp");
    }

}
