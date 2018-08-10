package br.com.sonner.estagio.servlet.tipologradouro;


import br.com.sonner.estagio.controller.TipoLogradouroControllerImpl;
import br.com.sonner.estagio.vos.TipologradouroFiltroVO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/pesquisa-tipologradouro")
public class Pesquisa extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TipoLogradouroControllerImpl tipologradouroController = new TipoLogradouroControllerImpl();
        
        TipologradouroFiltroVO vo = new TipologradouroFiltroVO();
        vo.setNome(request.getParameter("nome"));
        
        HttpSession session = request.getSession();
        session.setAttribute("filtro", vo);
        session.setAttribute("lista", tipologradouroController.filtrar(vo));

        response.sendRedirect("/views/tipologradouro/lista.jsp");
    }
}