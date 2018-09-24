package br.com.sonner.estagio.servlet.disciplina;

import br.com.sonner.estagio.controller.DisciplinaControllerImpl;
import br.com.sonner.estagio.controller.EscolaControllerImpl;
import br.com.sonner.estagio.controller.TurmaControllerImpl;
import br.com.sonner.estagio.controller.api.DisciplinaController;
import br.com.sonner.estagio.model.Disciplina;
import br.com.sonner.estagio.model.Escola;
import br.com.sonner.estagio.model.Turma;
import br.com.sonner.estagio.vos.DisciplinaFiltroVO;
import br.com.sonner.estagio.vos.TurmaFiltroVO;

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

        EscolaControllerImpl escolaController = new EscolaControllerImpl();

        DisciplinaFiltroVO vo = new DisciplinaFiltroVO();

        HttpSession session = req.getSession();

        Escola escola = null;
        String turma = "";

        if (req.getParameter("disciplina") != null && !req.getParameter("disciplina").isEmpty()) {
            turma = req.getParameter("disciplina");
        }
        if (req.getParameter("escola") != null && !req.getParameter("escola").isEmpty() && !req.getParameter("escola").equals("-1")) {
            escola = escolaController.getOne(Long.valueOf(req.getParameter("escola")));
        }


        Disciplina novaDisciplina = new Disciplina();
        novaDisciplina.setNome(turma);
        novaDisciplina.setEscola(escola);

        List<String> erros = disciplinaController.validation(novaDisciplina);


        if (erros.size() == 0) {
            vo.setEscola(novaDisciplina.getEscola().getId());
            vo.setNome(novaDisciplina.getNome());

            List<Disciplina> verifica = disciplinaController.filtrar(vo);

            if (verifica.size() == 0) {


                disciplinaController.save(novaDisciplina);
                vo.setNome("");
                vo.setEscola(null);

                session.setAttribute("listaTurma", (disciplinaController.filtrar(vo)));
                session.setAttribute("success", "Disciplina inserida com sucesso");

                res.sendRedirect("/views/disciplina/lista.jsp");
            } else {
                String existe = "Disciplina já cadastrada!";

                session.setAttribute("errors", existe);
                session.setAttribute("campoDisciplina", novaDisciplina);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/disciplina/insere.jsp");
                requestDispatcher.forward(req, res);
            }
        } else {
            session.setAttribute("errors", erros);
            session.setAttribute("campoDisciplina", novaDisciplina);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/disciplina/insere.jsp");
            requestDispatcher.forward(req, res);

        }

    }
}
