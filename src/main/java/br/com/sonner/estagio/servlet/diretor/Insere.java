package br.com.sonner.estagio.servlet.diretor;

import br.com.sonner.estagio.controller.*;
import br.com.sonner.estagio.model.Diretor;
import br.com.sonner.estagio.model.Funcionario;
import br.com.sonner.estagio.model.Pessoa;
import br.com.sonner.estagio.model.SexoEnum;
import br.com.sonner.estagio.vos.DiretorFiltroVO;
import br.com.sonner.estagio.vos.FuncionarioFiltroVO;

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

@WebServlet("/insere-diretor")
public class Insere extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DiretorControllerImpl diretorController = new DiretorControllerImpl();
        FuncionarioControllerImpl funcionarioController = new FuncionarioControllerImpl();
        PessoaControllerImpl pessoaController = new PessoaControllerImpl();
        EscolaControllerImpl escolaController = new EscolaControllerImpl();

        HttpSession session = request.getSession();
        Funcionario funcionario = new Funcionario();
        Pessoa pessoa = new Pessoa();
        Diretor diretor = new Diretor();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        SexoEnum sexo;

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
        pessoa.setAlunos(null);
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
        diretor.setFuncionario(funcionario);

        pessoaController.save(pessoa);
        funcionarioController.save(funcionario);
        diretorController.save(diretor);

        DiretorFiltroVO vo = new DiretorFiltroVO();

        vo.setFuncionario(new Funcionario());
        vo.getFuncionario().setPessoa(new Pessoa());

        session.setAttribute("listaDiretor", diretorController.filtrarLike(vo));
        session.setAttribute("success", "Diretor inserido com sucesso");
        response.sendRedirect("/views/diretor/lista.jsp");
    }
}