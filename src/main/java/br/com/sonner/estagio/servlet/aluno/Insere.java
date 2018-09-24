package br.com.sonner.estagio.servlet.aluno;

import br.com.sonner.estagio.controller.AlunoControllerImpl;
import br.com.sonner.estagio.controller.EnderecoControllerImpl;
import br.com.sonner.estagio.controller.PessoaControllerImpl;
import br.com.sonner.estagio.model.Aluno;
import br.com.sonner.estagio.model.Pessoa;
import br.com.sonner.estagio.model.SexoEnum;
import br.com.sonner.estagio.vos.AlunoFiltroVO;

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
        HttpSession session = request.getSession();
        AlunoControllerImpl alunoController = new AlunoControllerImpl();
        PessoaControllerImpl pessoaController = new PessoaControllerImpl();
        Aluno aluno = new Aluno();
        Pessoa pessoa = new Pessoa();
        SexoEnum sexo;

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date DATA;
        try {
            Date dataNascimento = formatter.parse(request.getParameter("data-nascimento"));
            pessoa.setDataNascimento(dataNascimento);
            DATA = dataNascimento;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        pessoa.setMae(request.getParameter("mae"));
        pessoa.setPai(request.getParameter("pai"));
        pessoa.setFuncionario(null);
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
        //salva o primeiro como pessoa mas nao salva como aluno
        alunoController.save(aluno);


        AlunoFiltroVO vo = new AlunoFiltroVO();
        vo.setPessoa(new Pessoa());

        session.setAttribute("listaAluno", alunoController.filtrar(vo));
        session.setAttribute("success", "Aluno inserido com sucesso");
        response.sendRedirect("/views/aluno/lista.jsp");
    }


}

