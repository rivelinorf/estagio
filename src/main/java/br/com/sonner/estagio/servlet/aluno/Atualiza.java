package br.com.sonner.estagio.servlet.aluno;

import br.com.sonner.estagio.controller.AlunoControllerImpl;
import br.com.sonner.estagio.model.Aluno;
import br.com.sonner.estagio.model.Pessoa;
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

@WebServlet("/atualiza-aluno")
public class Atualiza extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AlunoControllerImpl alunoController = new AlunoControllerImpl();
        HttpSession session = request.getSession();
        Aluno aluno = new Aluno();
        Pessoa pessoa = new Pessoa();
        AlunoFiltroVO vo = new AlunoFiltroVO(), voSession = (AlunoFiltroVO) session.getAttribute("alunoParaEditar");

        if (voSession.getPessoa().getNome().equals(request.getParameter("nome"))) {
            response.sendRedirect("/views/aluno/lista.jsp");
            return;
        }

        aluno = alunoController.getOne(Long.valueOf(request.getParameter("id")));

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date dataNascimento = formatter.parse(request.getParameter("data-nascimento"));
            aluno.getPessoa().setDataNascimento(dataNascimento);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        aluno.getPessoa().setNome(request.getParameter("nome"));
        aluno.getPessoa().setPai(request.getParameter("pai"));
        aluno.getPessoa().setMae(request.getParameter("mae"));
        aluno.getPessoa().setCpf(request.getParameter("cpf"));

        alunoController.update(aluno);

        vo.setPessoa(new Pessoa());
        vo.getPessoa().setAluno(new Aluno());

        session.setAttribute("listaDiretor", alunoController.filtrar(vo));
        session.setAttribute("success", "Aluno atualizado com sucesso");

        response.sendRedirect("/views/aluno/lista.jsp");
    }
}
