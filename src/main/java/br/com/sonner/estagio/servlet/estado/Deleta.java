package br.com.sonner.estagio.servlet.estado;

import br.com.sonner.estagio.controller.EstadoControllerImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/estado-deleta")
public class Deleta extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        new EstadoControllerImpl().delete(Long.valueOf(request.getParameter("id")));

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/estado/deleta.jsp");
        requestDispatcher.forward(request, response);
    }
}
