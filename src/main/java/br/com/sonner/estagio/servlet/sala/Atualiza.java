package br.com.sonner.estagio.servlet.sala;

import br.com.sonner.estagio.controller.EscolaControllerImpl;
import br.com.sonner.estagio.controller.SalaControllerImpl;
import br.com.sonner.estagio.model.parte2.segundo.Escola;
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
        SalaFiltroVO vo = new SalaFiltroVO();
        EscolaControllerImpl escolaController = new EscolaControllerImpl();


        HttpSession session = request.getSession();

        Escola escola = null;

        String sala = request.getParameter("sala");


        if (request.getParameter("escola") != null && !request.getParameter("escola").isEmpty()
                && !request.getParameter("escola").equals("-1")) {
            escola = escolaController.getOne(Long.valueOf(request.getParameter("escola")));
        }

        Sala novaSala = new Sala();
        novaSala.setId(Long.valueOf(request.getParameter("id")));
        novaSala.setNome(sala);
        novaSala.setEscola(escola);


        List<String> erros = salaController.validation(novaSala);

        if (erros.size() == 0) {
            vo.setEscola(novaSala.getEscola().getId());
            vo.setNome(novaSala.getNome());


            List<Sala> verifica = salaController.filtrar(vo);

            if (verifica.size() == 0) {
                salaController.update(novaSala);


                vo.setNome("");
                vo.setEscola(null);

                session.setAttribute("listaSala", salaController.filtrar(vo));
                session.setAttribute("success", "Sala atualizada com sucesso");
                session.setAttribute("filtroSala", null);
                response.sendRedirect("/views/sala/lista.jsp");
            } else {
                SalaFiltroVO salaantiga = (SalaFiltroVO) session.getAttribute("salaParaEditar");

                if (vo.getEscola().equals(salaantiga.getEscola()) && vo.getNome().equals(salaantiga.getNome())) {
                    vo.setNome("");
                    vo.setEscola(null);

                    session.setAttribute("listaSala", salaController.filtrar(vo));
                    session.setAttribute("filtroSala", null);
                    response.sendRedirect("/views/sala/lista.jsp");
                } else {

                    String existe = "Sala já cadastrado!";

                    session.setAttribute("errors", existe);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/sala/atualiza.jsp");
                    requestDispatcher.forward(request, response);
                }
            }

        } else {
            session.setAttribute("errors", erros);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/sala/atualiza.jsp");
            requestDispatcher.forward(request, response);
        }

    }

}
