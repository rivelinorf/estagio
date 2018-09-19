package br.com.sonner.estagio.servlet.turma;

import br.com.sonner.estagio.controller.TurmaControllerImpl;
import br.com.sonner.estagio.model.parte2.segundo.Turma;
import br.com.sonner.estagio.vos.TurmaFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/turma/preenche-vo")

public class PreencheVo extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        TurmaControllerImpl turmaController = new TurmaControllerImpl();
        TurmaFiltroVO vo = new TurmaFiltroVO();
        Turma turma = turmaController.getOne(Long.valueOf(request.getParameter("id")));


        vo.setId(turma.getId());
        vo.setNome(turma.getNome());
        vo.setEscola(turma.getEscola().getId());


        HttpSession session = request.getSession();
        session.setAttribute("turmaParaEditar", vo);

        response.sendRedirect("/views/turma/atualiza.jsp");
    }
}
