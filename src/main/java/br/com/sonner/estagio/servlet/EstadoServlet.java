package br.com.sonner.estagio.servlet;

import br.com.sonner.estagio.dao.EstadoDAOImpl;
import br.com.sonner.estagio.dao.api.EstadoDAO;
import br.com.sonner.estagio.model.Estado;

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
       /* Estado novoEstado = new Estado(req.getParameter("nome"), req.getParameter("abv"));
        new EstadoControllerImpl().save(novoEstado);*/

        EstadoDAO estadoDAO = new EstadoDAOImpl();
        Estado pegoPorId = estadoDAO.getOne(Long.valueOf(req.getParameter("id")));

        System.out.println(pegoPorId.getNome());

        RequestDispatcher rd = req.getRequestDispatcher("/lista-estados.jsp");
        rd.forward(req, res);
    }
}
