package br.com.sonner.estagio.servlet.disciplina;

import br.com.sonner.estagio.controller.DisciplinaControllerImpl;
import br.com.sonner.estagio.controller.TurmaControllerImpl;
import br.com.sonner.estagio.model.Disciplina;
import br.com.sonner.estagio.vos.DisciplinaFiltroVO;
import br.com.sonner.estagio.vos.TurmaFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/pesquisa-disciplina")

public class Pesquisa extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        DisciplinaControllerImpl disciplinaController = new DisciplinaControllerImpl();
        DisciplinaFiltroVO vo = new DisciplinaFiltroVO();

        vo.setNome(request.getParameter("disciplina"));

        if (request.getParameter("escola") != "" && request.getParameter("escola") != null) {
            vo.setEscola(Long.valueOf(request.getParameter("escola")));
        }


        HttpSession session = request.getSession();

        if (vo.getNome() == null && vo.getEscola() == null) {
            session.setAttribute("filtroDisciplina", null);
            session.setAttribute("listaDisciplina", null);
        } else {
            session.setAttribute("filtroDisciplina", vo);
            session.setAttribute("listaDisciplina", disciplinaController.filtrarLike(vo));
        }

        response.sendRedirect("/views/disciplina/lista.jsp");
    }
}
