package br.com.sonner.estagio.servlet.tipologradouro;

import br.com.sonner.estagio.controller.TipoLogradouroControllerImpl;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.LogradouroFiltroVO;
import br.com.sonner.estagio.vos.TipologradouroFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/deleta-tipologradouro")
public class Deleta extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        TipoLogradouroControllerImpl tipologradouroController = new TipoLogradouroControllerImpl();

        LogradouroFiltroVO logradouroFiltroVO = new LogradouroFiltroVO();
        HttpSession session = request.getSession();
        TipologradouroFiltroVO tipologradouroFiltroVO = (TipologradouroFiltroVO) session.getAttribute("filtroTipologradouro");

        logradouroFiltroVO.setNome("");
        logradouroFiltroVO.setTipologradouro(Long.valueOf(request.getParameter("id")));

        try {
            tipologradouroController.delete(Long.valueOf(request.getParameter("id")));
        } catch (CustomException e) {
            session.setAttribute("errors",e.getMessage());
        }

        if (tipologradouroFiltroVO == null) {
            tipologradouroFiltroVO = new TipologradouroFiltroVO();
            tipologradouroFiltroVO.setNome("");
        }

        session.setAttribute("listaTipologradouro", tipologradouroController.filtrar(tipologradouroFiltroVO));
        response.sendRedirect("/views/tipologradouro/lista.jsp");
    }
}
