package br.com.sonner.estagio.servlet.logradouro;

import br.com.sonner.estagio.controller.LogradouroControllerImpl;
import br.com.sonner.estagio.vos.LogradouroFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/pesquisa-logradouro")
public class Pesquisa extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LogradouroControllerImpl logradouroController = new LogradouroControllerImpl();
        LogradouroFiltroVO vo = new LogradouroFiltroVO();

        vo.setNome(request.getParameter("logradouro"));

        if (request.getParameter("tipologradouro") != "" && request.getParameter("tipologradouro") != null) {
            vo.setTipologradouro(Long.valueOf(request.getParameter("tipologradouro")));
        }

        if (request.getParameter("cidade") != "" && request.getParameter("cidade") != null) {
            vo.setCidade(Long.valueOf(request.getParameter("cidade")));
        }

        HttpSession session = request.getSession();

        if (vo.getNome() == null && vo.getTipologradouro() == null && vo.getCidade() == null) {
            session.setAttribute("filtroLogradouro", null);
            session.setAttribute("listaLogradouro", null);
        } else {

            session.setAttribute("filtroLogradouro", vo);
            session.setAttribute("listaLogradouro", logradouroController.filtrarLike(vo));
        }

        response.sendRedirect("/views/logradouro/lista.jsp");
    }
}
