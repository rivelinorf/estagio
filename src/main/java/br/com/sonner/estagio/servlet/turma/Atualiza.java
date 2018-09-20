package br.com.sonner.estagio.servlet.turma;

import br.com.sonner.estagio.controller.EscolaControllerImpl;
import br.com.sonner.estagio.controller.TurmaControllerImpl;
import br.com.sonner.estagio.controller.api.EscolaController;
import br.com.sonner.estagio.model.Turma;
import br.com.sonner.estagio.vos.TurmaFiltroVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/atualiza-turma")
public class Atualiza {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        TurmaControllerImpl turmaController = new TurmaControllerImpl();
        HttpSession session = request.getSession();
        TurmaFiltroVO vo = new TurmaFiltroVO(), voSession = (TurmaFiltroVO) session.getAttribute("turmaParaEditar");
        EscolaController escolaController = new EscolaControllerImpl();
        Turma novaturma = new Turma();

        if (voSession.getEscola().equals(Long.valueOf(request.getParameter("escola"))) && voSession.getNome().equals(request.getParameter("turma"))) {
            response.sendRedirect("/views/turma/lista.jsp");
            return;
        }

        session.setAttribute("filtroTurma", null);

        novaturma.setId(Long.valueOf(request.getParameter("id")));
        novaturma.setNome(request.getParameter("turma"));
        novaturma.setEscola(escolaController.getOne(Long.valueOf(request.getParameter("escola"))));

        novaturma.setMatriculas(null);
        novaturma.setTurmasDiscplina(null);

        List<String> erros = turmaController.validation(novaturma);

        if (erros.size() == 0) {
            vo.setId(null);
            vo.setNome(novaturma.getNome());
            vo.setEscola(novaturma.getEscola().getId());

            List<Turma> verifica = turmaController.filtrar(vo);

            if (verifica.size() == 0) {
                turmaController.update(novaturma);
                vo.setNome("");
                vo.setEscola(null);

                session.setAttribute("listaTurma", turmaController.filtrar(vo));
                session.setAttribute("success", "Turma atualizada com sucesso");
                response.sendRedirect("/views/turma/lista.jsp");

            } else {
                String existe = "";
                if (verifica.size() > 0) {
                    existe = "Turma ja cadastrada!";
                }
                session.setAttribute("errors", existe);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/turma/atualiza.jsp");
                requestDispatcher.forward(request, response);
            }

        } else {
            vo.setId(Long.valueOf(request.getParameter("id")));
            vo.setNome(request.getParameter("turma"));
            vo.setEscola(Long.valueOf(request.getParameter("escola")));

            session.setAttribute("errors", erros);
            session.setAttribute("turmaParaEditar", vo);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/turma/atualiza.jsp");
            requestDispatcher.forward(request, response);
        }

    }
}