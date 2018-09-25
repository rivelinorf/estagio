package br.com.sonner.estagio.servlet.diretor;

import br.com.sonner.estagio.controller.*;
import br.com.sonner.estagio.model.*;
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
import java.util.List;

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
        Diretor diretorValidation = new Diretor();

        diretorValidation.setFuncionario(new Funcionario());
        diretorValidation.getFuncionario().setPessoa(new Pessoa());
        diretorValidation.getFuncionario().getPessoa().setNome(request.getParameter("nome"));
        diretorValidation.getFuncionario().getPessoa().setCpf(request.getParameter("cpf"));

        List<String> erros = diretorController.validation(diretorValidation);

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
            diretor.setFuncionario(funcionario);

            pessoaController.save(pessoa);
            funcionarioController.save(funcionario);
            diretorController.save(diretor);

            DiretorFiltroVO vo = new DiretorFiltroVO();

            vo.setFuncionario(new Funcionario());
            vo.getFuncionario().setPessoa(new Pessoa());
            vo.getFuncionario().setEscola(new Escola());

            session.setAttribute("listaDiretor", diretorController.filtrarLike(vo));
            session.setAttribute("success", "Diretor inserido com sucesso");
            response.sendRedirect("/views/diretor/lista.jsp");
        } else {
            DiretorFiltroVO vo = new DiretorFiltroVO();

            vo.setFuncionario(new Funcionario());
            vo.getFuncionario().setPessoa(new Pessoa());
            vo.getFuncionario().setEscola(new Escola());

            session.setAttribute("listaDiretor", diretorController.filtrarLike(vo));
            session.setAttribute("errors", erros);
            response.sendRedirect("/views/diretor/insere.jsp");
        }
    }
}