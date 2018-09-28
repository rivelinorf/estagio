package br.com.sonner.estagio.servlet.nota;

import br.com.sonner.estagio.controller.*;
import br.com.sonner.estagio.controller.api.*;
import br.com.sonner.estagio.model.Disciplina;
import br.com.sonner.estagio.model.Nota;
import br.com.sonner.estagio.model.Turma;
import br.com.sonner.estagio.model.TurmaDisciplina;
import br.com.sonner.estagio.vos.NotaFiltroVO;
import br.com.sonner.estagio.vos.TurmaDisciplinaFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/pesquisa-nota")
public class Pesquisa extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        TurmaDisciplinaFiltroVO turmaDisciplinaFiltroVO = new TurmaDisciplinaFiltroVO();
        HttpSession session = request.getSession();
        AlunoController alunoController = new AlunoControllerImpl();
        Nota nota = new Nota();
        TurmaDisciplina turmaDisciplina = new TurmaDisciplina();
        TurmaController turmaController = new TurmaControllerImpl();
        TurmaDisciplinaController turmaDisciplinaController = new TurmaDisciplinaControllerImpl();
        DisciplinaController disciplinaController = new DisciplinaControllerImpl();
        NotaController notaController = new NotaControllerImpl();
        NotaFiltroVO notaFiltroVO = new NotaFiltroVO();

        turmaDisciplinaFiltroVO.setDisciplina(null);
        turmaDisciplinaFiltroVO.setTurma(null);
        Disciplina disciplina = new Disciplina();
        Turma turma = new Turma();

        if (request.getParameter("turma") != "" && request.getParameter("turma") != null) {
            turmaDisciplinaFiltroVO.setTurma(Long.valueOf(request.getParameter("turma")));
        }

        if (request.getParameter("disciplina") != "" && request.getParameter("disciplina") != null) {
            turmaDisciplinaFiltroVO.setDisciplina(Long.valueOf(request.getParameter("disciplina")));
        }

        if (turmaDisciplinaFiltroVO.getDisciplina() != null && turmaDisciplinaFiltroVO.getDisciplina() != null) {
            disciplina = disciplinaController.getOne(turmaDisciplinaFiltroVO.getDisciplina());
            turma = turmaController.getOne(turmaDisciplinaFiltroVO.getTurma());
            List<TurmaDisciplina> turmaDisciplinas = turmaDisciplinaController.pesquisaTurma(turmaDisciplinaFiltroVO);
            if (turmaDisciplinas.size() > 0) {
                turmaDisciplina = turmaDisciplinas.get(0);
                notaFiltroVO.setTurmaDisciplina(turmaDisciplina.getId());
            }

        }


        if (turmaDisciplinaFiltroVO.getDisciplina() == null && turmaDisciplinaFiltroVO.getTurma() == null) { // primeira vez entrando na pagina
            session.setAttribute("filtroTurmaDisciplina", null);
            session.setAttribute("listaAluno", null);
            session.setAttribute("campoNota", null);
            session.setAttribute("filtroTurmaDisciplina", null);
            session.setAttribute("turma", null);
            session.setAttribute("disciplina", null);
            session.setAttribute("listaNota", null);

        } else if (turmaDisciplina != null) {
            session.setAttribute("filtroTurmaDisciplina", turmaDisciplinaFiltroVO);
            session.setAttribute("listaAluno", alunoController.pesquisaAlunoPorTurmaDisciplina(turmaDisciplinaFiltroVO));
            session.setAttribute("turma", turma);
            session.setAttribute("disciplina", disciplina);
            session.setAttribute("listaNota", notaController.pesquisaNota(notaFiltroVO));
            session.setAttribute("campoNota", notaController.pesquisaNota(notaFiltroVO).size());

        } else  if(turmaDisciplina == null){
            session.setAttribute("filtroTurmaDisciplina", turmaDisciplinaFiltroVO);
            session.setAttribute("listaAluno", alunoController.pesquisaAlunoPorTurmaDisciplina(turmaDisciplinaFiltroVO));
            session.setAttribute("turma", turma);
            session.setAttribute("disciplina", disciplina);
            session.setAttribute("listaNota", null);
            session.setAttribute("campoNota", null);

        }
        else {
            session.setAttribute("errors", "Insira turma e disciplina");
            session.setAttribute("filtroTurmaDisciplina", turmaDisciplinaFiltroVO);

        }

        response.sendRedirect("/views/nota/insere.jsp");
    }
}
