package br.com.sonner.estagio.servlet.disciplina;

import br.com.sonner.estagio.controller.DisciplinaControllerImpl;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.DisciplinaFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/deleta-disciplina")

public class Deleta extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DisciplinaControllerImpl disciplinaController = new DisciplinaControllerImpl();
        HttpSession session = request.getSession();

        DisciplinaFiltroVO disciplinaFiltroVO = (DisciplinaFiltroVO) session.getAttribute("filtroDisciplina");

        try {
            disciplinaController.delete(Long.valueOf(request.getParameter("id")));
            session.setAttribute("success", "Disciplina deletada com sucesso");

        } catch (CustomException e) {
            session.setAttribute("errors", e.getMessage());
        }

        if (disciplinaFiltroVO == null) {
            disciplinaFiltroVO = new DisciplinaFiltroVO();
        }

        session.setAttribute("listaDisciplina", disciplinaController.filtrar(disciplinaFiltroVO));
        response.sendRedirect("/views/disciplina/lista.jsp");
    }

}
