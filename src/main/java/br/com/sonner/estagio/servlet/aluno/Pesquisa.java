package br.com.sonner.estagio.servlet.aluno;

import br.com.sonner.estagio.controller.AlunoControllerImpl;
import br.com.sonner.estagio.controller.FuncionarioControllerImpl;
import br.com.sonner.estagio.vos.FuncionarioFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/pesquisa-aluno")
public class Pesquisa extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        AlunoControllerImpl alunoController = new AlunoControllerImpl();


        /*FuncionarioFiltroVO vo = new FuncionarioFiltroVO();
        vo.setFuncionario(request.getParameter("funcionario"));
        vo.setAbv(request.getParameter("abv"));

        HttpSession session = request.getSession();

        if (vo.getFuncionario() == null && vo.getAbv() == null) {
            session.setAttribute("filtroFuncionario", null);
            session.setAttribute("listaFuncionario", null);

        } else {
            session.setAttribute("filtroFuncionario", vo);
            session.setAttribute("listaFuncionario", funcionarioController.filtrarLike(vo));
        }
        */

        response.sendRedirect("/views/aluno/lista.jsp");
    }
}
