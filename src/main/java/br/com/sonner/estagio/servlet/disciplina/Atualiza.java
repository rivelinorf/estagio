package br.com.sonner.estagio.servlet.disciplina;

import br.com.sonner.estagio.controller.DisciplinaControllerImpl;
import br.com.sonner.estagio.model.parte2.segundo.Disciplina;
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
public class Atualiza  extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        DisciplinaControllerImpl disciplinaController = new DisciplinaControllerImpl();
        HttpSession session = request.getSession();
        DisciplinaFiltroVO vo = new DisciplinaFiltroVO(),
                voSession = (DisciplinaFiltroVO) session.getAttribute("disciplina-para-editar");
        Disciplina disciplina = new Disciplina();

        if (voSession.getNome().equals(request.getParameter("disciplina"))) {
            session.setAttribute("filtroDisciplina", null);
            response.sendRedirect("/views/disciplina/lista.jsp");
            return;
        }

        disciplina.setNome(request.getParameter("disciplina"));
        disciplina.setId(Long.valueOf(request.getParameter("id")));

        List<String> erros = disciplinaController.validation(disciplina);

        if (erros.size() == 0) {
            vo.setNome("");
            vo.setNome(disciplina.getNome());

            List<Disciplina> verifica = disciplinaController.filtrar(vo);

            if (verifica.size() == 0) {

                disciplinaController.update(disciplina);
                vo.setNome("");

                session.setAttribute("listaDisciplina", disciplinaController.filtrar(vo));
                session.setAttribute("filtroDisciplina", null);

                response.sendRedirect("/views/disciplina/lista.jsp");
            } else {
                String existe = "Disciplina  já cadastrada !";

                session.setAttribute("errors", existe);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/disciplina/atualiza.jsp");
                requestDispatcher.forward(request, response);
            }
        } else {
            session.setAttribute("errors", erros);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/disciplina/atualiza.jsp");
            requestDispatcher.forward(request, response);
        }


    }
}