package br.com.sonner.estagio.servlet.turma;

import br.com.sonner.estagio.controller.CidadeControllerImpl;
import br.com.sonner.estagio.controller.TurmaControllerImpl;
import br.com.sonner.estagio.model.parte2.segundo.Turma;
import br.com.sonner.estagio.vos.CidadeFiltroVO;
import br.com.sonner.estagio.vos.TurmaFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/pesquisa-turma")

public class Pesquisa extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        TurmaControllerImpl turmaController = new TurmaControllerImpl();
        TurmaFiltroVO vo = new TurmaFiltroVO();

        vo.setNome(request.getParameter("turma"));

        if (request.getParameter("escola") != "" && request.getParameter("escola") != null) {
            vo.setEscola(Long.valueOf(request.getParameter("escola")));
        }


        HttpSession session = request.getSession();

        if (vo.getNome() == null && vo.getEscola()==null) {
            session.setAttribute("filtroTurma", null);
            session.setAttribute("listaTurma", null);
        } else {
            session.setAttribute("filtroTurma", vo);
            session.setAttribute("listaTurma", turmaController.filtrarLike(vo));
        }

        response.sendRedirect("/views/turma/lista.jsp");
    }
}
