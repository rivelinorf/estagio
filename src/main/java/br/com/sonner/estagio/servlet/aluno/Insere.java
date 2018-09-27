package br.com.sonner.estagio.servlet.aluno;

import br.com.sonner.estagio.controller.*;
import br.com.sonner.estagio.model.*;
import br.com.sonner.estagio.vos.AlunoFiltroVO;
import br.com.sonner.estagio.vos.DiretorFiltroVO;

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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet("/insere-aluno")
public class Insere extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlunoControllerImpl alunoController = new AlunoControllerImpl();
        PessoaControllerImpl pessoaController = new PessoaControllerImpl();
        MatriculaControllerImpl matriculaController = new MatriculaControllerImpl();

        HttpSession session = request.getSession();
        Aluno aux = new Aluno();
        Matricula matricula =  new Matricula();
        aux.setMatricula(null);
        aux.setPessoa(null);
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

        List<String> erros = pessoaController.validation(pessoa);

        if (erros.size() == 0) {
            aluno.setPessoa(pessoa);
            pessoa.setAluno(aluno);
            pessoaController.save(pessoa);

            alunoController.save(aluno);

            AlunoFiltroVO alunoFiltroVO =  new AlunoFiltroVO();
            alunoFiltroVO.setPessoa(pessoa);

            aluno = alunoController.filtrar(alunoFiltroVO).get(0);

            String codigoMatricula = Integer.toString(Calendar.getInstance().get(Calendar.YEAR)) + Long.toString(aluno.getId());

            matricula.setAluno(aluno);
            matricula.setData(new Date());
            matricula.setNumero(Integer.parseInt(codigoMatricula));

            aluno.setMatricula(matricula);

            alunoController.update(aluno);
            matriculaController.save(matricula);

            AlunoFiltroVO vo = new AlunoFiltroVO();
            vo.setPessoa(new Pessoa());
            vo.getPessoa().setAluno(new Aluno());

            session.setAttribute("listaAluno", alunoController.filtrarLike(vo));
            session.setAttribute("success", "Aluno inserido com sucesso");
            response.sendRedirect("/views/aluno/lista.jsp");
        } else {
            session.setAttribute("errors", erros);
            session.setAttribute("campoAluno", aux);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/aluno/insere.jsp");
            requestDispatcher.forward(request, response);
        }

    }


}

