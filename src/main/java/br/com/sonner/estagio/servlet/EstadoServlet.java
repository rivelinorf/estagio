package br.com.sonner.estagio.servlet;

import br.com.sonner.estagio.controller.EstadoControllerImpl;
import br.com.sonner.estagio.controller.api.EstadoController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/estado-insere")
public class EstadoServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
       /* Estado teste = new Estado(req.getParameter("nome"), req.getParameter("abv"));
        teste.setId(23);*/
        EstadoController estadoController = new EstadoControllerImpl();

        /*estadoController.save(teste);*/
        /*estadoController.update(teste);*/
        /*System.out.println(estadoController.getOne(23).getNome());*/
        /*estadoController.delete(23);*/

        RequestDispatcher rd = req.getRequestDispatcher("/lista-estados.jsp");
        rd.forward(req, res);
    }
}
