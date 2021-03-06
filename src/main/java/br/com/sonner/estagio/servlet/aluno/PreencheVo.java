package br.com.sonner.estagio.servlet.aluno;

import br.com.sonner.estagio.controller.AlunoControllerImpl;
import br.com.sonner.estagio.model.Aluno;
import br.com.sonner.estagio.vos.AlunoFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/aluno/preenche-vo")
public class PreencheVo extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AlunoControllerImpl alunoController = new AlunoControllerImpl();
        AlunoFiltroVO vo = new AlunoFiltroVO();
        HttpSession session = request.getSession();

        Aluno aluno = alunoController.getOne(Long.valueOf(request.getParameter("id")));
        vo.setId(aluno.getId());
        vo.setPessoa(aluno.getPessoa());

        session.setAttribute("alunoParaEditar", vo);
        response.sendRedirect("/views/aluno/atualiza.jsp");
    }
}
