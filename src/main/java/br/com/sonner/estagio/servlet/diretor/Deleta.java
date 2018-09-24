package br.com.sonner.estagio.servlet.diretor;

import br.com.sonner.estagio.controller.DiretorControllerImpl;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.DiretorFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/diretor-deleta")
public class Deleta extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DiretorControllerImpl diretorController = new DiretorControllerImpl();
        HttpSession session = request.getSession();
        DiretorFiltroVO diretorFiltroVO = (DiretorFiltroVO) session.getAttribute("filtroDiretor");

        try {
            diretorController.delete(Long.valueOf(request.getParameter("id")));
            session.setAttribute("success", "Diretor deletada com sucesso");
        } catch (CustomException e){
            session.setAttribute("errors", e.getMessage());
        }

        if (diretorFiltroVO == null) {
            diretorFiltroVO = new DiretorFiltroVO();
        }

        session.setAttribute("listaDiretor", diretorController.filtrar(diretorFiltroVO));
        response.sendRedirect("/views/diretor/lista.jsp");
    }
}
