package br.com.sonner.estagio.servlet.Aluno;

import br.com.sonner.estagio.controller.AlunoControllerImpl;
import br.com.sonner.estagio.model.parte2.segundo.Aluno;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/aluno/preenche-vo")
public class PreencheVo extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlunoControllerImpl alunoController = new AlunoControllerImpl();
        //AlunoFiltroVO vo = new AlunoFiltroVO();

        Aluno aluno = alunoController.getOne(Long.valueOf(request.getParameter("id")));
        //vo.setId(aluno.getId());
       // vo.setPessoa(aluno.getPessoa().getId());


        HttpSession session = request.getSession();
        //session.setAttribute("alunoParaEditar", vo);

        response.sendRedirect("/views/aluno/atualiza.jsp");
    }
}
