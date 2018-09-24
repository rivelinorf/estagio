package br.com.sonner.estagio.servlet.disciplina;

import br.com.sonner.estagio.controller.EscolaControllerImpl;
import br.com.sonner.estagio.controller.DisciplinaControllerImpl;
import br.com.sonner.estagio.controller.api.EscolaController;
import br.com.sonner.estagio.model.Disciplina;
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

@WebServlet("/atualiza-disciplina")
public class Atualiza extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DisciplinaControllerImpl disciplinaController = new DisciplinaControllerImpl();
        HttpSession session = request.getSession();
        DisciplinaFiltroVO vo = new DisciplinaFiltroVO(), voSession = (DisciplinaFiltroVO) session.getAttribute("disciplinaParaEditar");
        EscolaController escolaController = new EscolaControllerImpl();
        Disciplina novadisciplina = new Disciplina();

        if (voSession.getEscola().equals(Long.valueOf(request.getParameter("escola"))) && voSession.getNome().equals(request.getParameter("disciplina"))) {
            response.sendRedirect("/views/disciplina/lista.jsp");
            return;
        }

        session.setAttribute("filtroDisciplina", null);

        novadisciplina.setId(Long.valueOf(request.getParameter("id")));
        novadisciplina.setNome(request.getParameter("disciplina"));
        novadisciplina.setEscola(escolaController.getOne(Long.valueOf(request.getParameter("escola"))));


        List<String> erros = disciplinaController.validation(novadisciplina);

        if (erros.size() == 0) {
            vo.setId(null);
            vo.setNome(novadisciplina.getNome());
            vo.setEscola(novadisciplina.getEscola().getId());

            List<Disciplina> verifica = disciplinaController.filtrar(vo);

            if (verifica.size() == 0) {
                disciplinaController.update(novadisciplina);
                vo.setNome("");
                vo.setEscola(null);

                session.setAttribute("listaDisciplina", disciplinaController.filtrar(vo));
                session.setAttribute("success", "Disciplina atualizada com sucesso");
                response.sendRedirect("/views/disciplina/lista.jsp");

            } else {
                String existe = "";
                if (verifica.size() > 0) {
                    existe = "Disciplina ja cadastrada!";
                }
                session.setAttribute("errors", existe);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/disciplina/atualiza.jsp");
                requestDispatcher.forward(request, response);
            }

        } else {
            vo.setId(Long.valueOf(request.getParameter("id")));
            vo.setNome(request.getParameter("disciplina"));
            vo.setEscola(Long.valueOf(request.getParameter("escola")));

            session.setAttribute("errors", erros);
            session.setAttribute("disciplinaParaEditar", vo);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/disciplina/atualiza.jsp");
            requestDispatcher.forward(request, response);
        }

    }
}