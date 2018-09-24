package br.com.sonner.estagio.servlet.turma;

import br.com.sonner.estagio.controller.EscolaControllerImpl;
import br.com.sonner.estagio.controller.TurmaControllerImpl;
import br.com.sonner.estagio.model.Escola;
import br.com.sonner.estagio.model.Turma;
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

@WebServlet("/insere-turma")
public class Insere extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        TurmaControllerImpl turmaController = new TurmaControllerImpl();

        EscolaControllerImpl escolaController = new EscolaControllerImpl();

        TurmaFiltroVO vo = new TurmaFiltroVO();

        HttpSession session = req.getSession();

        Escola escola = null;
        String turma = req.getParameter("turma");

        if (req.getParameter("escola") != null && !req.getParameter("escola").isEmpty() && !req.getParameter("escola").equals("-1")) {
            escola = escolaController.getOne(Long.valueOf(req.getParameter("escola")));
        }

        Turma novaturma = new Turma();
        novaturma.setNome(turma);
        novaturma.setEscola(escola);

        List<String> erros = turmaController.validation(novaturma);


        if (erros.size() == 0) {
            vo.setEscola(novaturma.getEscola().getId());
            vo.setNome(novaturma.getNome());

            List<Turma> verifica = turmaController.filtrar(vo);

            if (verifica.size() == 0) {


                turmaController.save(novaturma);
                vo.setNome("");
                vo.setEscola(null);

                session.setAttribute("listaTurma", (turmaController.filtrar(vo)));
                session.setAttribute("success", "Turma inserida com sucesso");

                res.sendRedirect("/views/turma/lista.jsp");
            } else {
                String existe = "Turma já cadastrada!";

                session.setAttribute("errors", existe);
                session.setAttribute("campoTurma", novaturma);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/turma/insere.jsp");
                requestDispatcher.forward(req, res);
            }
        } else {
            session.setAttribute("errors", erros);
            session.setAttribute("campoTurma", novaturma);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/turma/insere.jsp");
            requestDispatcher.forward(req, res);

        }

    }
}
