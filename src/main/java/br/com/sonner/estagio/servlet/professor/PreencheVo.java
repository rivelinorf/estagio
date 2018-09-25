package br.com.sonner.estagio.servlet.professor;

import br.com.sonner.estagio.controller.ProfessorControllerImpl;
import br.com.sonner.estagio.model.Professor;
import br.com.sonner.estagio.vos.ProfessorFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/professor/preenche-vo")
public class PreencheVo extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ProfessorControllerImpl professorController = new ProfessorControllerImpl();
        ProfessorFiltroVO vo = new ProfessorFiltroVO();

        Professor professor = professorController.getOne(Long.valueOf(request.getParameter("id")));
        vo.setId(professor.getId());
        vo.setFuncionario(professor.getFuncionario());
        vo.getFuncionario().setEscola(professor.getFuncionario().getEscola());

        try {
            vo.getFuncionario().setAdmissao(new SimpleDateFormat("dd/MM/yyyy").parse(professor.getFuncionario().getAdmissao()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession();
        session.setAttribute("professorParaEditar", vo);

        response.sendRedirect("/views/professor/atualiza.jsp");
    }
}