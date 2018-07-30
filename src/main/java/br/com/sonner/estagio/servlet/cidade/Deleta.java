package br.com.sonner.estagio.servlet.cidade;

import br.com.sonner.estagio.controller.CidadeControllerImpl;
import br.com.sonner.estagio.controller.api.CidadeController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cidade-deleta")
public class Deleta extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CidadeController cidadeController = new CidadeControllerImpl();
        cidadeController.delete(Long.valueOf(request.getParameter("id")));

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/cidade/lista.jsp");
        requestDispatcher.forward(request, response);
    }
}
