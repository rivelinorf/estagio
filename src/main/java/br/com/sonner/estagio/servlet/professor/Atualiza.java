package br.com.sonner.estagio.servlet.professor;

import br.com.sonner.estagio.controller.ProfessorControllerImpl;
import br.com.sonner.estagio.controller.EscolaControllerImpl;
import br.com.sonner.estagio.model.Professor;
import br.com.sonner.estagio.model.Escola;
import br.com.sonner.estagio.model.Funcionario;
import br.com.sonner.estagio.model.Pessoa;
import br.com.sonner.estagio.vos.ProfessorFiltroVO;

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

@WebServlet("/atualiza-professor")
public class Atualiza extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProfessorControllerImpl professorController = new ProfessorControllerImpl();
        EscolaControllerImpl escolaController = new EscolaControllerImpl();
        HttpSession session = request.getSession();
        Professor professor = new Professor();
        Pessoa pessoa = new Pessoa();
        Funcionario funcionario = new Funcionario();
        ProfessorFiltroVO vo = new ProfessorFiltroVO(), voSession = (ProfessorFiltroVO) session.getAttribute("professorParaEditar");

        if (voSession.getFuncionario().getPessoa().getNome().equals(request.getParameter("nome"))) {
            response.sendRedirect("/views/professor/lista.jsp");
            return;
        }

        professor = professorController.getOne(Long.valueOf(request.getParameter("id")));

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date dataNascimento = formatter.parse(request.getParameter("data-nascimento"));
            Date dataAdmissao = formatter.parse(request.getParameter("data-admissao"));
            professor.getFuncionario().setAdmissao(dataAdmissao);
            professor.getFuncionario().getPessoa().setDataNascimento(dataNascimento);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        professor.getFuncionario().getPessoa().setNome(request.getParameter("nome"));
        professor.getFuncionario().getPessoa().setPai(request.getParameter("pai"));
        professor.getFuncionario().getPessoa().setMae(request.getParameter("mae"));
        professor.getFuncionario().getPessoa().setCpf(request.getParameter("cpf"));
        professor.getFuncionario().setEscola(escolaController.getOne(Long.valueOf(request.getParameter("escola"))));

        professorController.update(professor);

        vo.setFuncionario(new Funcionario());
        vo.getFuncionario().setPessoa(new Pessoa());
        vo.getFuncionario().setEscola(new Escola());

        session.setAttribute("listaProfessor", professorController.filtrar(vo));
        session.setAttribute("success", "Professor atualizado com sucesso");

        response.sendRedirect("/views/professor/lista.jsp");
    }
}
