package br.com.sonner.estagio.servlet.estado;

import br.com.sonner.estagio.controller.EstadoControllerImpl;
import br.com.sonner.estagio.model.Estado;
import br.com.sonner.estagio.vos.EstadoFiltroVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/atualiza-estado")
public class Atualiza extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EstadoControllerImpl estadoController = new EstadoControllerImpl();
        EstadoFiltroVO vo = new EstadoFiltroVO();
        HttpSession session = request.getSession();
        Estado estado = new Estado();

        if (request.getParameter("estado") != "" && request.getParameter("abv") != "") {
            estado.setNome(request.getParameter("estado"));
            estado.setAbv(request.getParameter("abv"));
            estado.setId(Long.valueOf(request.getParameter("id")));

            estadoController.update(estado);

            vo.setEstado("");
            vo.setAbv("");
            session.setAttribute("listaEstado", estadoController.filtrar(vo));

            response.sendRedirect("/views/estado/lista.jsp");
        } else {
            session.setAttribute("errors", "Impossivel atualizar se existir campos vazios");
            response.sendRedirect("/views/estado/atualiza.jsp");
        }
    }
}
