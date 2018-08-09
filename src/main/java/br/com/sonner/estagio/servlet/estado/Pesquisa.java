package br.com.sonner.estagio.servlet.estado;

import br.com.sonner.estagio.controller.EstadoControllerImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/pesquisa-estado")
public class Pesquisa extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EstadoControllerImpl estadoController = new EstadoControllerImpl();
        estadoController.setEstadosPesquisados(request.getParameter("estado"), request.getParameter("abv"));

        response.sendRedirect("/views/estado/lista.jsp");
    }
}
