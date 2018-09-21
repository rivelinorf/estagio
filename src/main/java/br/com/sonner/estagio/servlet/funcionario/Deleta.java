package br.com.sonner.estagio.servlet.funcionario;

import br.com.sonner.estagio.controller.FuncionarioControllerImpl;
import br.com.sonner.estagio.model.Pessoa;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.FuncionarioFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/funcionario-deleta")
public class Deleta extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        FuncionarioControllerImpl funcionarioController = new FuncionarioControllerImpl();
        HttpSession session = request.getSession();

        try {
            funcionarioController.delete(Long.valueOf(request.getParameter("id")));
        } catch (CustomException e) {
            session.setAttribute("errors", e.getMessage());
        }

        FuncionarioFiltroVO vo = new FuncionarioFiltroVO();

        vo.setPessoa(new Pessoa());

        session.setAttribute("listaFuncionario", funcionarioController.filtrar(vo));
        response.sendRedirect("/views/funcionario/lista.jsp");
    }
}
