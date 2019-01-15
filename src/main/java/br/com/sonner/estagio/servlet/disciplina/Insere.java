package br.com.sonner.estagio.servlet.disciplina;

import br.com.sonner.estagio.controller.EscolaControllerImpl;
import br.com.sonner.estagio.controller.DisciplinaControllerImpl;
import br.com.sonner.estagio.controller.TurmaControllerImpl;
import br.com.sonner.estagio.controller.TurmaDisciplinaControllerImpl;
import br.com.sonner.estagio.controller.api.TurmaController;
import br.com.sonner.estagio.model.Escola;
import br.com.sonner.estagio.model.Disciplina;
import br.com.sonner.estagio.model.TurmaDisciplina;
import br.com.sonner.estagio.vos.DisciplinaFiltroVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/insere-disciplina")
public class Insere extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        DisciplinaControllerImpl disciplinaController = new DisciplinaControllerImpl();
        TurmaDisciplinaControllerImpl turmaDisciplinaController = new TurmaDisciplinaControllerImpl();
        TurmaControllerImpl turmaController = new TurmaControllerImpl();
        TurmaDisciplina turmaDisciplina = new TurmaDisciplina();
        EscolaControllerImpl escolaController = new EscolaControllerImpl();

        DisciplinaFiltroVO vo = new DisciplinaFiltroVO();

        HttpSession session = req.getSession();

        Escola escola = null;
        String disciplina = "";

        if (req.getParameter("disciplina") != null && !req.getParameter("disciplina").isEmpty()) {
            disciplina = req.getParameter("disciplina");
        }
        if (req.getParameter("escola") != null && !req.getParameter("escola").isEmpty() && !req.getParameter("escola").equals("-1")) {
            escola = escolaController.getOne(Long.valueOf(req.getParameter("escola")));
        }


        Disciplina novadisciplina = new Disciplina();
        novadisciplina.setNome(disciplina);
        novadisciplina.setEscola(escola);

        List<String> erros = disciplinaController.validation(novadisciplina);


        if (erros.size() == 0) {
            vo.setEscola(novadisciplina.getEscola().getId());
            vo.setNome(novadisciplina.getNome());

            List<Disciplina> verifica = disciplinaController.filtrar(vo);

            if (verifica.size() == 0) {

                disciplinaController.save(novadisciplina);

                turmaDisciplina.setDisciplina(novadisciplina);
                turmaDisciplina.setTurma(turmaController.getOne(Long.valueOf(req.getParameter("turma"))));
                turmaDisciplinaController.save(turmaDisciplina);

                vo.setNome("");
                vo.setEscola(null);

                session.setAttribute("listaDisciplina", (disciplinaController.filtrar(vo)));
                session.setAttribute("success", "Disciplina inserida com sucesso");

                res.sendRedirect("/views/disciplina/lista.jsp");
            } else {
                String existe = "Disciplina já cadastrada!";

                session.setAttribute("errors", existe);
                session.setAttribute("campoDisciplina", novadisciplina);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/disciplina/insere.jsp");
                requestDispatcher.forward(req, res);
            }
        } else {
            session.setAttribute("errors", erros);
            session.setAttribute("campoDisciplina", novadisciplina);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/disciplina/insere.jsp");
            requestDispatcher.forward(req, res);

        }

    }
}
