package br.com.sonner.estagio.servlet.disciplina;

import br.com.sonner.estagio.controller.DisciplinaControllerImpl;
import br.com.sonner.estagio.model.Disciplina;
import br.com.sonner.estagio.vos.DisciplinaFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/disciplina/preenche-vo")

public class PreencheVo extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        DisciplinaControllerImpl disciplinaController = new DisciplinaControllerImpl();

        DisciplinaFiltroVO vo = new DisciplinaFiltroVO();

        Disciplina disciplina = disciplinaController.getOne(Long.valueOf(request.getParameter("id")));

        vo.setId(disciplina.getId());
        vo.setNome(disciplina.getNome());

        HttpSession session = request.getSession();
        session.setAttribute("disciplina-para-editar", vo);

        response.sendRedirect("/views/disciplina/atualiza.jsp");
    }
}
