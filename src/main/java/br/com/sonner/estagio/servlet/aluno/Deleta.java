package br.com.sonner.estagio.servlet.aluno;

import br.com.sonner.estagio.controller.AlunoControllerImpl;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.AlunoFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/aluno-deleta")
public class Deleta extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AlunoControllerImpl alunoController = new AlunoControllerImpl();
        HttpSession session = request.getSession();
        AlunoFiltroVO alunoFiltroVO = (AlunoFiltroVO) session.getAttribute("filtroAluno");

        try {
            alunoController.delete(Long.valueOf(request.getParameter("id")));
            session.setAttribute("success", "Aluno deletado com sucesso");
        } catch (CustomException e) {
            session.setAttribute("errors", e.getMessage());
        }

        if (alunoFiltroVO == null) {
            alunoFiltroVO = new AlunoFiltroVO();
        }

        session.setAttribute("listaAluno", alunoController.filtrar(alunoFiltroVO));
        response.sendRedirect("/views/aluno/lista.jsp");
    }
}
