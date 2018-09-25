package br.com.sonner.estagio.servlet.professor;

import br.com.sonner.estagio.controller.ProfessorControllerImpl;
import br.com.sonner.estagio.controller.EscolaControllerImpl;
import br.com.sonner.estagio.model.Escola;
import br.com.sonner.estagio.model.Funcionario;
import br.com.sonner.estagio.model.Pessoa;
import br.com.sonner.estagio.vos.ProfessorFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/pesquisa-professor")
public class Pesquisa extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        ProfessorControllerImpl professorController = new ProfessorControllerImpl();
        EscolaControllerImpl escolaController = new EscolaControllerImpl();
        HttpSession session = request.getSession();
        ProfessorFiltroVO vo = new ProfessorFiltroVO();
        Pessoa pessoa = new Pessoa();
        Funcionario funcionario = new Funcionario();

        pessoa.setNome(request.getParameter("nome"));
        pessoa.setCpf(request.getParameter("cpf"));

        if (request.getParameter("escola") != null && request.getParameter("escola") != "") {
            funcionario.setEscola(escolaController.getOne(Long.valueOf(request.getParameter("escola"))));
        } else {
            funcionario.setEscola(new Escola());
        }


        funcionario.setPessoa(pessoa);
        vo.setFuncionario(funcionario);

        if (pessoa.getNome() == null && pessoa.getCpf() == null) { // primeira vez entrando na pagina
            session.setAttribute("filtroProfessor", null);
            session.setAttribute("listaProfessor", null);
        } else {
            session.setAttribute("filtroProfessor", vo);
            session.setAttribute("listaProfessor", professorController.filtrarLike(vo));
        }

        response.sendRedirect("/views/professor/lista.jsp");
    }
}