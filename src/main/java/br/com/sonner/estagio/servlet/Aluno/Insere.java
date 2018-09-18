package br.com.sonner.estagio.servlet.Aluno;

import br.com.sonner.estagio.controller.AlunoControllerImpl;
import br.com.sonner.estagio.controller.PessoaControllerImpl;
import br.com.sonner.estagio.controller.api.PessoaController;
import br.com.sonner.estagio.model.parte2.primeiro.Pessoa;
import br.com.sonner.estagio.model.parte2.segundo.Aluno;
import br.com.sonner.estagio.vos.AlunoFiltroVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/insere-aluno")
public class Insere extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        AlunoControllerImpl alunoController = new AlunoControllerImpl();
        PessoaController pessoaController = new PessoaControllerImpl();
        AlunoFiltroVO vo = new AlunoFiltroVO();
        HttpSession session = req.getSession();
        Aluno novoAluno = new Aluno();

        Pessoa pessoa = null;

    }
}
