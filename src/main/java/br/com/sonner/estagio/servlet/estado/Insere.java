package br.com.sonner.estagio.servlet.estado;

import br.com.sonner.estagio.controller.EstadoControllerImpl;
import br.com.sonner.estagio.model.Estado;
import br.com.sonner.estagio.vos.EstadoFiltroVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/insere-estado")
public class Insere extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Estado aux = new Estado();
        EstadoControllerImpl estadoController = new EstadoControllerImpl();
        HttpSession session = request.getSession();
        EstadoFiltroVO vo = new EstadoFiltroVO();

        aux.setNome(request.getParameter("nome"));
        aux.setAbv(request.getParameter("abv"));
        List<String> erros =  estadoController.validation(aux);

        if (erros.size() == 0) {
            estadoController.save(aux);

            vo.setEstado("");
            vo.setAbv("");
            session.setAttribute("listaEstado", estadoController.filtrar(vo));
            session.setAttribute("success", "Estado inserido com sucesso");

            response.sendRedirect("/views/estado/lista.jsp");
        } else {
            session.setAttribute("errors", erros);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/estado/insere.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
