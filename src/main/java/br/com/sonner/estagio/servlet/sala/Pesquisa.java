package br.com.sonner.estagio.servlet.sala;

import br.com.sonner.estagio.controller.SalaControllerImpl;
import br.com.sonner.estagio.vos.SalaFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/pesquisa-sala")
public class Pesquisa extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        SalaControllerImpl salaController = new SalaControllerImpl();
        SalaFiltroVO vo = new SalaFiltroVO();

        vo.setNome(request.getParameter("sala"));


        if (request.getParameter("escola") != "" && request.getParameter("escola") != null) {
            vo.setEscola(Long.valueOf(request.getParameter("escola")));
        }

        HttpSession session = request.getSession();

        if (vo.getNome() == null  && vo.getEscola() == null) {
            session.setAttribute("filtroSala", null);
            session.setAttribute("listaSala", null);
        } else {

            session.setAttribute("filtroSala", vo);
            session.setAttribute("listaSala", salaController.filtrarLike(vo));
        }

        response.sendRedirect("/views/sala/lista.jsp");
    }
}
