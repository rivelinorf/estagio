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

@WebServlet("/estado-insere")
public class Insere extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        EstadoController estadoController = new EstadoControllerImpl();
        Estado novo = new Estado();

        novo.setNome(req.getParameter("nome"));
        novo.setAbv(req.getParameter("abv"));

        estadoController.save(novo);

        RequestDispatcher rd = req.getRequestDispatcher("/estado/lista.jsp");
        rd.forward(req, res);
    }
}
