package br.com.sonner.estagio.servlet.disciplina;

import br.com.sonner.estagio.controller.DisciplinaControllerImpl;
import br.com.sonner.estagio.controller.TipoLogradouroControllerImpl;
import br.com.sonner.estagio.controller.api.DisciplinaController;
import br.com.sonner.estagio.model.parte2.segundo.TurmaDisciplina;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.DisciplinaFiltroVO;
import br.com.sonner.estagio.vos.LogradouroFiltroVO;
import br.com.sonner.estagio.vos.TipologradouroFiltroVO;
import br.com.sonner.estagio.vos.TurmaDisciplinaFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/deleta-disciplina")

public class Deleta extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        DisciplinaControllerImpl disciplinaController = new DisciplinaControllerImpl();

        TurmaDisciplinaFiltroVO turmaDisciplinaFiltroVO = new TurmaDisciplinaFiltroVO();

        HttpSession session = request.getSession();

        DisciplinaFiltroVO disciplinaFiltroVO = (DisciplinaFiltroVO) session.getAttribute("filtroDisciplina");

        turmaDisciplinaFiltroVO.setDisciplina(Long.valueOf(request.getParameter("id")));


        try {
            disciplinaController.delete(Long.valueOf(request.getParameter("id")));
        } catch (CustomException e) {
            session.setAttribute("errors", e.getMessage());
        }

        if (disciplinaFiltroVO == null) {
            disciplinaFiltroVO = new DisciplinaFiltroVO();
            disciplinaFiltroVO.setNome("");
        }

        session.setAttribute("listaDisciplina", disciplinaController.filtrar(disciplinaFiltroVO));
        response.sendRedirect("/views/disciplina/lista.jsp");
    }
}
