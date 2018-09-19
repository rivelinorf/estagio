package br.com.sonner.estagio.servlet.sala;

import br.com.sonner.estagio.controller.EscolaControllerImpl;
import br.com.sonner.estagio.controller.SalaControllerImpl;
import br.com.sonner.estagio.model.parte2.segundo.Sala;
import br.com.sonner.estagio.vos.SalaFiltroVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/atualiza-sala")
public class Atualiza extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        SalaControllerImpl salaController = new SalaControllerImpl();
        HttpSession session = request.getSession();
        SalaFiltroVO vo = new SalaFiltroVO(), voSession = (SalaFiltroVO) session.getAttribute("salaParaEditar");
        EscolaControllerImpl escolaController = new EscolaControllerImpl();
        Sala novaSala = new Sala();



        if (voSession.getEscola().equals(Long.valueOf(request.getParameter("escola"))) && voSession.getNome().equals(request.getParameter("sala"))) {
            response.sendRedirect("/views/sala/lista.jsp");
            return;
        }

        session.setAttribute("filtroSala", null);

        novaSala.setId(Long.valueOf(request.getParameter("id")));
        novaSala.setNome(request.getParameter("sala"));
        novaSala.setEscola(escolaController.getOne(Long.valueOf(request.getParameter("escola"))));


        List<String> erros = salaController.validation(novaSala);

        if (erros.size() == 0) {
            vo.setId(null);
            vo.setEscola(novaSala.getEscola().getId());
            vo.setNome(novaSala.getNome());

            List<Sala> verifica = salaController.filtrar(vo);

            if (verifica.size() == 0) {
                salaController.update(novaSala);


                vo.setNome("");
                vo.setEscola(null);

                session.setAttribute("listaSala", salaController.filtrar(vo));
                session.setAttribute("success", "Sala atualizada com sucesso");
                response.sendRedirect("/views/sala/lista.jsp");
            } else {
                String existe = "";
                if (verifica.size() > 0) {
                    existe = "Sala ja cadastrada!";
                }

                session.setAttribute("errors", existe);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/sala/atualiza.jsp");
                requestDispatcher.forward(request, response);
            }
        } else {
            vo.setId(Long.valueOf(request.getParameter("id")));
            vo.setNome(request.getParameter("sala"));
            vo.setEscola(Long.valueOf(request.getParameter("escola")));

            session.setAttribute("errors", erros);
            session.setAttribute("salaParaEditar", vo);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/sala/atualiza.jsp");
            requestDispatcher.forward(request, response);

        }

    }

}

