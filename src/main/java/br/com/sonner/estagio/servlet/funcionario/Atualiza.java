package br.com.sonner.estagio.servlet.funcionario;

import br.com.sonner.estagio.controller.FuncionarioControllerImpl;
import br.com.sonner.estagio.model.Funcionario;
import br.com.sonner.estagio.model.Pessoa;
import br.com.sonner.estagio.vos.FuncionarioFiltroVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/atualiza-funcionario")
public class Atualiza extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FuncionarioControllerImpl funcionarioController = new FuncionarioControllerImpl();
        HttpSession session = request.getSession();
        Funcionario funcionario = new Funcionario();
        Pessoa pessoa = new Pessoa();
        FuncionarioFiltroVO vo = new FuncionarioFiltroVO(), voSession = (FuncionarioFiltroVO) session.getAttribute("funcionario-para-editar");

        /*if (voSession.getPessoa().getNome().equals(request.getParameter("nome")) && voSession.getAbv().equals(request.getParameter("abv"))) {
            response.sendRedirect("/views/funcionario/lista.jsp");
            return;
        }*/

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date dataNascimento = formatter.parse(request.getParameter("data-nascimento"));
            Date dataAdmissao = formatter.parse(request.getParameter("data-admissao"));
            funcionario.setAdmissao(dataAdmissao);
            pessoa.setDataNascimento(dataNascimento);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        funcionario.setId(Long.valueOf(request.getParameter("id")));
        pessoa.setNome(request.getParameter("nome"));
        pessoa.setPai(request.getParameter("pai"));
        pessoa.setMae(request.getParameter("mae"));
        funcionario.setPessoa(pessoa);
        funcionarioController.update(funcionario);

        vo.setPessoa(new Pessoa());

        session.setAttribute("listaFuncionario", funcionarioController.filtrar(vo));
        session.setAttribute("success", "Funcionario atualizado com sucesso");

        response.sendRedirect("/views/funcionario/lista.jsp");
    }
}
