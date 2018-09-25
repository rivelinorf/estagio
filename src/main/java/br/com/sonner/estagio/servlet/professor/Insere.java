package br.com.sonner.estagio.servlet.professor;

import br.com.sonner.estagio.controller.*;
import br.com.sonner.estagio.model.*;
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
import java.util.List;

@WebServlet("/insere-professor")
public class Insere extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProfessorControllerImpl professorController = new ProfessorControllerImpl();
        FuncionarioControllerImpl funcionarioController = new FuncionarioControllerImpl();
        PessoaControllerImpl pessoaController = new PessoaControllerImpl();
        EscolaControllerImpl escolaController = new EscolaControllerImpl();

        HttpSession session = request.getSession();
        Funcionario funcionario = new Funcionario();
        Pessoa pessoa = new Pessoa();
        Professor professor = new Professor();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        SexoEnum sexo;
        Professor professorValidation = new Professor();

        professorValidation.setFuncionario(new Funcionario());
        professorValidation.getFuncionario().setPessoa(new Pessoa());
        professorValidation.getFuncionario().getPessoa().setNome(request.getParameter("nome"));
        professorValidation.getFuncionario().getPessoa().setCpf(request.getParameter("cpf"));

        List<String> erros = professorController.validation(professorValidation);

        if (erros.size() ==  0) {
            try {
                Date dataNascimento = formatter.parse(request.getParameter("data-nascimento"));
                Date dataAdmissao = formatter.parse(request.getParameter("data-admissao"));
                pessoa.setDataNascimento(dataNascimento);
                funcionario.setAdmissao(dataAdmissao);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            pessoa.setMae(request.getParameter("mae"));
            pessoa.setPai(request.getParameter("pai"));
            pessoa.setEndereco(new EnderecoControllerImpl().getOne(1));
            pessoa.setNome(request.getParameter("nome"));
            pessoa.setCpf(request.getParameter("cpf"));

            if (request.getParameter("sexo") != null) {
                sexo = (request.getParameter("sexo").equals("m")) ? SexoEnum.MASCULINO : SexoEnum.FEMININO;
                pessoa.setSexo(sexo);
            }

            funcionario.setEscola(escolaController.getOne(Long.valueOf(request.getParameter("escola"))));
            funcionario.setPessoa(pessoa);
            pessoa.setFuncionario(funcionario);
            professor.setFuncionario(funcionario);

            pessoaController.save(pessoa);
            funcionarioController.save(funcionario);
            professorController.save(professor);

            ProfessorFiltroVO vo = new ProfessorFiltroVO();

            vo.setFuncionario(new Funcionario());
            vo.getFuncionario().setPessoa(new Pessoa());
            vo.getFuncionario().setEscola(new Escola());

            session.setAttribute("listaProfessor", professorController.filtrarLike(vo));
            session.setAttribute("success", "Professor inserido com sucesso");
            response.sendRedirect("/views/professor/lista.jsp");
        } else {
            ProfessorFiltroVO vo = new ProfessorFiltroVO();

            vo.setFuncionario(new Funcionario());
            vo.getFuncionario().setPessoa(new Pessoa());
            vo.getFuncionario().setEscola(new Escola());

            session.setAttribute("listaProfessor", professorController.filtrarLike(vo));
            session.setAttribute("errors", erros);
            response.sendRedirect("/views/professor/insere.jsp");
        }
    }
}