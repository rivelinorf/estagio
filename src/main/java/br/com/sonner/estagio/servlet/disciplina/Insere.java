package br.com.sonner.estagio.servlet.disciplina;

import br.com.sonner.estagio.controller.DisciplinaControllerImpl;
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

@WebServlet("/insere-disciplina")
public class Insere  extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Disciplina aux = new Disciplina();
        DisciplinaControllerImpl disciplinaController = new DisciplinaControllerImpl();
        HttpSession session = request.getSession();
        DisciplinaFiltroVO vo = new DisciplinaFiltroVO();
        aux.setNome("");

        if (request.getParameter("nome") != "" && request.getParameter("nome") != null) {
            aux.setNome(request.getParameter("nome"));
        }

        List<String> erros = disciplinaController.validation(aux);

        session.setAttribute("filtroDisciplina", null);

        if (erros.size() == 0) {

            vo.setNome("");
            vo.setNome(aux.getNome());

            List<Disciplina> verifica = disciplinaController.filtrar(vo);

            if (verifica.size() == 0) {
                //if (true) {
                disciplinaController.save(aux);
                vo.setNome("");

                session.setAttribute("listaDisciplina", (disciplinaController.filtrar(vo)));
                session.setAttribute("success", "Disciplina  inserida com sucesso");
                session.setAttribute("filtroDisciplina", null);

                response.sendRedirect("/views/disciplina/lista.jsp");
            } else {
                String existe = "Disciplina já cadastrado!";

                session.setAttribute("errors", existe);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/disciplina/insere.jsp");
                requestDispatcher.forward(request, response);

            }
        } else {
            session.setAttribute("errors", erros);
            session.setAttribute("campoDisciplina", aux);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/disciplina/insere.jsp");
            requestDispatcher.forward(request, response);
        }

    }
}
