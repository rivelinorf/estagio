package br.com.sonner.estagio.servlet.turma;

import br.com.sonner.estagio.controller.TurmaControllerImpl;
import br.com.sonner.estagio.vos.TurmaFiltroVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/busca-turma")
public class BuscaTurma extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        TurmaControllerImpl turmaController = new TurmaControllerImpl();
        TurmaFiltroVO turmaFiltroVO = new TurmaFiltroVO();

        turmaFiltroVO.setEscola(Long.valueOf(request.getParameter("escola")));

        session.setAttribute("turmaEscola", turmaController.filtrar(turmaFiltroVO));
        session.setAttribute("escolaId", request.getParameter("escola"));

        response.sendRedirect("/views/aluno/insere.jsp");
    }
}
