package br.com.sonner.estagio.servlet.logradouro;

import br.com.sonner.estagio.controller.LogradouroControllerImpl;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.EnderecoFiltroVO;
import br.com.sonner.estagio.vos.LogradouroFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/deleta-logradouro")
public class Deleta extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LogradouroControllerImpl logradouroController = new LogradouroControllerImpl();

        HttpSession session = request.getSession();
        LogradouroFiltroVO logradouroFiltroVO = (LogradouroFiltroVO) session.getAttribute("filtroLogradouro");

        EnderecoFiltroVO enderecoFiltroVO = new EnderecoFiltroVO();

        enderecoFiltroVO.setLogradouro(Long.valueOf(request.getParameter("id")));
        enderecoFiltroVO.setBairro(null);
        enderecoFiltroVO.setCep("");
        enderecoFiltroVO.setComplemento("");
        enderecoFiltroVO.setNumero(null);


        try {
            logradouroController.delete(Long.valueOf(request.getParameter("id")));

        } catch (CustomException e) {
            session.setAttribute("errors", e.getMessage());
        }

        if (logradouroFiltroVO == null) {
            logradouroFiltroVO = new LogradouroFiltroVO();
        }

        session.setAttribute("listaLogradouro", logradouroController.filtrar(logradouroFiltroVO));
        response.sendRedirect("/views/logradouro/lista.jsp");
    }
}
