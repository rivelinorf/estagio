package br.com.sonner.estagio.servlet.estado;

import br.com.sonner.estagio.controller.EstadoControllerImpl;
import br.com.sonner.estagio.controller.api.EstadoController;
import br.com.sonner.estagio.model.Estado;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/estado-atualiza")
public class Atualiza extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EstadoController estadoController = new EstadoControllerImpl();
        Estado estado = new Estado();

        estado.setId(Long.valueOf(request.getParameter("id")));
        estado.setNome(request.getParameter("nome"));
        estado.setAbv(request.getParameter("abv"));

        estadoController.update(estado);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/estado/lista.jsp");
        requestDispatcher.forward(request, response);
    }
}
