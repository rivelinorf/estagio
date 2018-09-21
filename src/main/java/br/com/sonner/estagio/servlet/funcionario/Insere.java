package br.com.sonner.estagio.servlet.funcionario;

import br.com.sonner.estagio.controller.EnderecoControllerImpl;
import br.com.sonner.estagio.controller.FuncionarioControllerImpl;
import br.com.sonner.estagio.controller.PessoaControllerImpl;
import br.com.sonner.estagio.model.Funcionario;
import br.com.sonner.estagio.model.Pessoa;
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

@WebServlet("/insere-funcionario")
public class Insere extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        FuncionarioControllerImpl funcionarioController = new FuncionarioControllerImpl();
        PessoaControllerImpl pessoaController = new PessoaControllerImpl();
        Funcionario funcionario = new Funcionario();
        Pessoa pessoa = new Pessoa();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

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

        funcionario.setPessoa(pessoa);
        pessoa.setFuncionario(funcionario);

        pessoaController.save(pessoa);
        funcionarioController.save(funcionario);

        FuncionarioFiltroVO vo = new FuncionarioFiltroVO();

        vo.setPessoa(new Pessoa());

        session.setAttribute("listaFuncionario", funcionarioController.filtrar(vo));
        session.setAttribute("success", "Funcionario inserido com sucesso");
        response.sendRedirect("/views/funcionario/lista.jsp");
    }
}