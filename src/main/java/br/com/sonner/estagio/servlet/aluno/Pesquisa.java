package br.com.sonner.estagio.servlet.aluno;

import br.com.sonner.estagio.controller.*;
import br.com.sonner.estagio.controller.api.MatriculaController;
import br.com.sonner.estagio.model.*;
import br.com.sonner.estagio.vos.AlunoFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/pesquisa-aluno")
public class Pesquisa extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        AlunoControllerImpl alunoController = new AlunoControllerImpl();
        MatriculaController matriculaController = new MatriculaControllerImpl();
        HttpSession session = request.getSession();
        AlunoFiltroVO vo = new AlunoFiltroVO();
        Aluno aluno = new Aluno();
        Pessoa pessoa = new Pessoa();
        SexoEnum sexo;
        String nome = "";
        Matricula matricula = null;

        if (request.getParameter("nome") != null && request.getParameter("nome") != "") {
            nome = (request.getParameter("nome"));
        }
        pessoa.setNome(nome);

//        if (request.getParameter("matricula") != null && request.getParameter("matricula") != "") {
//            matricula = matriculaController.getOne(Long.valueOf(request.getParameter("matricula")));
//        }
//        aluno.setMatricula(matricula);

        pessoa.setAluno(aluno);
        vo.setPessoa(pessoa);

        if (pessoa.getNome() == null && pessoa.getSexo() == null) { // primeira vez entrando na pagina
            session.setAttribute("filtroAluno", null);
            session.setAttribute("listaAluno", null);
        } else {
            session.setAttribute("filtroAluno", vo);
            session.setAttribute("listaAluno", alunoController.filtrarLike(vo));
        }

        response.sendRedirect("/views/aluno/lista.jsp");
    }
}
