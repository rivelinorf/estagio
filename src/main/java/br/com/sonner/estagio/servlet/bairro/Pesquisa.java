package br.com.sonner.estagio.servlet.bairro;

import br.com.sonner.estagio.controller.BairroControllerImpl;
import br.com.sonner.estagio.vos.BairroFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/pesquisa-bairro")
public class Pesquisa extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws  IOException {
        BairroControllerImpl bairroController = new BairroControllerImpl();
        BairroFiltroVO vo = new BairroFiltroVO();

        vo.setNome(request.getParameter("nome"));

        if (request.getParameter("cidadeID") != "" && request.getParameter("cidadeID") != null) {
            vo.setCidade(Long.valueOf(request.getParameter("cidadeID")));
        }

        HttpSession session = request.getSession();

        if (vo.getCidade() == null && vo.getNome() == null) {
            session.setAttribute("filtroBairro", null);
            session.setAttribute("listaBairro", null);
        } else {
            session.setAttribute("filtroBairro", vo);
            session.setAttribute("listaBairro", bairroController.filtrar(vo));
        }

        response.sendRedirect("/views/bairro/lista.jsp");

    }

}
