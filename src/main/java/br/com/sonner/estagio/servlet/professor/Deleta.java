package br.com.sonner.estagio.servlet.professor;

import br.com.sonner.estagio.controller.ProfessorControllerImpl;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.ProfessorFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/professor-deleta")
public class Deleta extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ProfessorControllerImpl professorController = new ProfessorControllerImpl();
        HttpSession session = request.getSession();
        ProfessorFiltroVO professorFiltroVO = (ProfessorFiltroVO) session.getAttribute("filtroProfessor");

        try {
            professorController.delete(Long.valueOf(request.getParameter("id")));
            session.setAttribute("success", "Professor deletada com sucesso");
        } catch (CustomException e){
            session.setAttribute("errors", e.getMessage());
        }

        if (professorFiltroVO == null) {
            professorFiltroVO = new ProfessorFiltroVO();
        }

        session.setAttribute("listaProfessor", professorController.filtrar(professorFiltroVO));
        response.sendRedirect("/views/professor/lista.jsp");
    }
}
