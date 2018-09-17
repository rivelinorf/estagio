package br.com.sonner.estagio.servlet.cidade;

import br.com.sonner.estagio.controller.CidadeControllerImpl;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.CidadeFiltroVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/cidade-deleta")
public class Deleta extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CidadeControllerImpl cidadeController = new CidadeControllerImpl();
        HttpSession session = request.getSession();
        CidadeFiltroVO cidadeFiltroVO = (CidadeFiltroVO) session.getAttribute("filtroCidade");

        try {
            cidadeController.delete(Long.valueOf(request.getParameter("id")));
            session.setAttribute("success", "Cidade deletada com sucesso");
        } catch (CustomException e){
            session.setAttribute("errors", e.getMessage());
        }

        if (cidadeFiltroVO == null) {
            cidadeFiltroVO = new CidadeFiltroVO();
        }

        session.setAttribute("listaCidade", cidadeController.filtrar(cidadeFiltroVO));
        response.sendRedirect("/views/cidade/lista.jsp");
    }
}
