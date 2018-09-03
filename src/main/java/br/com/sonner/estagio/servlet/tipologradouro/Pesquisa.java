package br.com.sonner.estagio.servlet.tipologradouro;

import br.com.sonner.estagio.controller.TipoLogradouroControllerImpl;
import br.com.sonner.estagio.vos.TipologradouroFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/pesquisa-tipologradouro")
public class Pesquisa extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        TipoLogradouroControllerImpl tipologradouroController = new TipoLogradouroControllerImpl();

        TipologradouroFiltroVO vo = new TipologradouroFiltroVO();
        vo.setNome(request.getParameter("tipologradouro"));

        HttpSession session = request.getSession();

        if (vo.getNome() == null) {
            session.setAttribute("filtroTipologradouro", null);
            session.setAttribute("listaTipologradouro", null);
        } else {

            session.setAttribute("filtroTipologradouro", vo);
            session.setAttribute("listaTipologradouro", tipologradouroController.filtrarLike(vo));
        }

        response.sendRedirect("/views/tipologradouro/lista.jsp");
    }
}
