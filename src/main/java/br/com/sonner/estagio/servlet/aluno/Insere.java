package br.com.sonner.estagio.servlet.aluno;

import br.com.sonner.estagio.controller.*;
import br.com.sonner.estagio.model.*;
import br.com.sonner.estagio.vos.AlunoFiltroVO;
import br.com.sonner.estagio.vos.DiretorFiltroVO;

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

@WebServlet("/insere-aluno")
public class Insere extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlunoControllerImpl alunoController = new AlunoControllerImpl();
        PessoaControllerImpl pessoaController = new PessoaControllerImpl();

        HttpSession session = request.getSession();
        Pessoa pessoa = new Pessoa();
        Aluno aluno = new Aluno();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        SexoEnum sexo;

        try {
            Date dataNascimento = formatter.parse(request.getParameter("data-nascimento"));
            pessoa.setDataNascimento(dataNascimento);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        pessoa.setMae(request.getParameter("mae"));
        pessoa.setPai(request.getParameter("pai"));
        pessoa.setAluno(null);
        pessoa.setEndereco(new EnderecoControllerImpl().getOne(1));
        pessoa.setNome(request.getParameter("nome"));
        pessoa.setCpf(request.getParameter("cpf"));

        if (request.getParameter("sexo") != null) {
            sexo = (request.getParameter("sexo").equals("m")) ? SexoEnum.MASCULINO : SexoEnum.FEMININO;
            pessoa.setSexo(sexo);
        }

        aluno.setPessoa(pessoa);
        pessoa.setAluno(aluno);

        pessoaController.save(pessoa);
        alunoController.save(aluno);

        AlunoFiltroVO vo = new AlunoFiltroVO();

        vo.setPessoa(new Pessoa());
        vo.getPessoa().setAluno(new Aluno());

        session.setAttribute("listaAluno", alunoController.filtrarLike(vo));
        session.setAttribute("success", "Aluno inserido com sucesso");
        response.sendRedirect("/views/aluno/lista.jsp");
    }

}

