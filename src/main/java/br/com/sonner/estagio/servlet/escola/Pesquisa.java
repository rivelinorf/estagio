package br.com.sonner.estagio.servlet.escola;

import br.com.sonner.estagio.controller.EscolaControllerImpl;
import br.com.sonner.estagio.vos.EscolaFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/pesquisa-escola")
public class Pesquisa extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        EscolaControllerImpl escolaController = new EscolaControllerImpl();
        EscolaFiltroVO vo = new EscolaFiltroVO();

        vo.setNome(request.getParameter("nome"));
        HttpSession session = request.getSession();

        if (vo.getNome() == null) {
            session.setAttribute("filtroEscola", null);
            session.setAttribute("listaEscola", null);
        } else {
            session.setAttribute("filtroEscola", vo);
            //session.setAttribute("listaEscola", escolaController.filtrar(vo));
            //session.setAttribute("listaEscola", escolaController.filtrarLike(vo));
        }

        response.sendRedirect("/views/bairro/lista.jsp");
    }
}
