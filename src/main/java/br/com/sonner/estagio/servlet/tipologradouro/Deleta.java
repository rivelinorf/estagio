package br.com.sonner.estagio.servlet.tipologradouro;

import br.com.sonner.estagio.controller.LogradouroControllerImpl;
import br.com.sonner.estagio.controller.TipoLogradouroControllerImpl;
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
        LogradouroControllerImpl logradouroController = new LogradouroControllerImpl();
        TipologradouroFiltroVO vo = new TipologradouroFiltroVO();
        LogradouroFiltroVO logradouroFiltroVO = new LogradouroFiltroVO();
        HttpSession session = request.getSession();


        logradouroFiltroVO.setNome("");
        logradouroFiltroVO.setTipologradouro(Long.valueOf(request.getParameter("id")));

        if (logradouroController.filtrar(logradouroFiltroVO).size() == 0) {
            tipologradouroController.delete(Long.valueOf(request.getParameter("id")));
            session.setAttribute("success", "Tipo logradouro deletado com sucesso");

        } else {
            session.setAttribute("errors", "Impossivel deletar!, Tipo logradouro possui relacionamento");

        }

        vo.setNome("");

        session.setAttribute("listaTipologradouro", tipologradouroController.filtrar(vo));
        response.sendRedirect("/views/tipologradouro/lista.jsp");

    }
}
