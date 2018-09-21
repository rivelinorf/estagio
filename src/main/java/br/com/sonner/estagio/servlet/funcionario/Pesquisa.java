package br.com.sonner.estagio.servlet.funcionario;

import br.com.sonner.estagio.controller.FuncionarioControllerImpl;
import br.com.sonner.estagio.model.Pessoa;
import br.com.sonner.estagio.vos.FuncionarioFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;

@WebServlet("/pesquisa-funcionario")
public class Pesquisa extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        FuncionarioControllerImpl funcionarioController = new FuncionarioControllerImpl();
        FuncionarioFiltroVO vo = new FuncionarioFiltroVO();
        HttpSession session = request.getSession();

        Pessoa pessoa = new Pessoa();

        pessoa.setNome(request.getParameter("nome"));

        vo.setPessoa(pessoa);

        if (vo.getPessoa().getNome() == null) { // primeira vez entrando na pagina
            session.setAttribute("filtroFuncionario", null);
            session.setAttribute("listaFuncionario", null);
        } else {
            session.setAttribute("filtroFuncionario", vo);
            session.setAttribute("listaFuncionario", funcionarioController.filtrarLike(vo));
        }

        response.sendRedirect("/views/funcionario/lista.jsp");
    }
}
