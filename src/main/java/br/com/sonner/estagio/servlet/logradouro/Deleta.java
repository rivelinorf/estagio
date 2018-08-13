package br.com.sonner.estagio.servlet.logradouro;

import br.com.sonner.estagio.controller.LogradouroControllerImpl;
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
        LogradouroFiltroVO vo = new LogradouroFiltroVO();

        logradouroController.delete(Long.valueOf(request.getParameter("id")));

        vo.setNome("");
        vo.setCidade(null);
        vo.setTipologradouro(null);

        HttpSession session = request.getSession();
        session.setAttribute("listaLogradouro", logradouroController.filtrar(vo));

        response.sendRedirect("/views/logradouro/lista.jsp");
    }
}
