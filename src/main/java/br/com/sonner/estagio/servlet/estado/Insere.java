package br.com.sonner.estagio.servlet.estado;

import br.com.sonner.estagio.controller.EstadoControllerImpl;
import br.com.sonner.estagio.model.Estado;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/insere-estado")
public class Insere extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Estado aux = new Estado();
        EstadoControllerImpl estadoController = new EstadoControllerImpl();

        aux.setNome(request.getParameter("nome"));
        aux.setAbv(request.getParameter("abv"));

        estadoController.save(aux);

        response.sendRedirect("/views/estado/lista.jsp");
    }
}
