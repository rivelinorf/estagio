package br.com.sonner.estagio.servlet.logradouro;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sonner.estagio.controller.LogradouroControllerImpl;
import br.com.sonner.estagio.vos.LogradouroFiltroVO;

@WebServlet("/pesquisa-logradouro")
public class Pesquisa extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        LogradouroControllerImpl logradouroController = new LogradouroControllerImpl();
        LogradouroFiltroVO vo = new LogradouroFiltroVO();

        vo.setNome(request.getParameter("logradouro"));


        if (request.getParameter("cidade") != "") {
            vo.setCidade(Long.valueOf(request.getParameter("cidade")));
        }

        if (request.getParameter("tipologradouro") != "") {
            vo.setTipologradouro(Long.valueOf(request.getParameter("tipologradouro")));
        }


        HttpSession session = request.getSession();
        session.setAttribute("filtroLogradouro", vo);
        session.setAttribute("listaLogradouro", logradouroController.filtrar(vo));

        response.sendRedirect("/views/logradouro/lista.jsp");
    }
}
