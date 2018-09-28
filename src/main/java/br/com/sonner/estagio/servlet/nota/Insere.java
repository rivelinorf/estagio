package br.com.sonner.estagio.servlet.nota;

import br.com.sonner.estagio.controller.*;
import br.com.sonner.estagio.controller.api.AlunoController;
import br.com.sonner.estagio.controller.api.DisciplinaController;
import br.com.sonner.estagio.controller.api.TurmaController;
import br.com.sonner.estagio.controller.api.TurmaDisciplinaController;
import br.com.sonner.estagio.model.*;
import br.com.sonner.estagio.vos.NotaFiltroVO;
import br.com.sonner.estagio.vos.TurmaDisciplinaFiltroVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/insere-nota")
public class Insere extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        NotaControllerImpl notaController = new NotaControllerImpl();
        TurmaController turmaController = new TurmaControllerImpl();
        DisciplinaController disciplinaController = new DisciplinaControllerImpl();
        AlunoController alunoController = new AlunoControllerImpl();
        TurmaDisciplinaController turmaDisciplinaController = new TurmaDisciplinaControllerImpl();
        NotaFiltroVO vo = new NotaFiltroVO();
        HttpSession session = req.getSession();
        Nota novaNota = new Nota();
        BigDecimal nota = null;
        Turma turma = null;
        Disciplina disciplina = null;
        Aluno aluno = null;
        TurmaDisciplina turmaDisciplina = new TurmaDisciplina();
        TurmaDisciplinaFiltroVO turmaDisciplinaFiltroVO = new TurmaDisciplinaFiltroVO();

        if (req.getParameter("turma") != "" && req.getParameter("turma") != null) {
            turma = turmaController.getOne(Long.valueOf(req.getParameter("turma")));
            req.getParameterValues("turma");
        }

        if (req.getParameter("disciplina") != "" && req.getParameter("disciplina") != null) {
            disciplina = disciplinaController.getOne(Long.valueOf(req.getParameter("disciplina")));
        }

        if (disciplina != null && turma != null) {
            turmaDisciplinaFiltroVO.setDisciplina(disciplina.getId());
            turmaDisciplinaFiltroVO.setTurma(turma.getId());

            List<TurmaDisciplina> turmaDisciplinas = turmaDisciplinaController.pesquisaTurma(turmaDisciplinaFiltroVO);

            if (turmaDisciplinas.size() == 0) {
                turmaDisciplina.setTurma(turma);
                turmaDisciplina.setDisciplina(disciplina);
                turmaDisciplinaController.save(turmaDisciplina);

                turmaDisciplinas = turmaDisciplinaController.pesquisaTurma(turmaDisciplinaFiltroVO);

            }

            turmaDisciplina = turmaDisciplinas.get(0);
        }

        if (req.getParameter("aluno") != "" && req.getParameter("aluno") != null) {
            aluno = alunoController.getOne(Long.valueOf(req.getParameter("aluno")));

        }

        if (req.getParameter("nota") != "" && req.getParameter("nota") != null) {
            nota = new BigDecimal(req.getParameter("nota"));
        }

        novaNota.setNota(nota);
        novaNota.setTurmaDisciplina(turmaDisciplina);
        novaNota.setAluno(aluno);

        vo.setAluno(aluno.getId());
        vo.setNota(nota);
        vo.setTurmaDisciplina(turmaDisciplina.getId());

        List<String> erros = notaController.validation(novaNota);

        if (erros.size() == 0) {
            vo.setNota(null);

            List<Nota> verifica = notaController.pesquisaNota(vo);

            if (verifica.size() == 0) {
                notaController.save(novaNota);
            } else {
                novaNota.setId(verifica.get(0).getId());
                notaController.update(novaNota);
            }

            session.setAttribute("success", "Nota lançada com sucesso");
            session.setAttribute("filtroTurmaDisciplina", null);
            session.setAttribute("listaAluno", null);
            session.setAttribute("campoNota", null);
            session.setAttribute("filtroTurmaDisciplina", null);
            session.setAttribute("turma", null);
            session.setAttribute("disciplina", null);


        } else {
            session.setAttribute("errors", erros);

        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/nota/insere.jsp");
        requestDispatcher.forward(req, res);

    }
}
