package br.com.sonner.estagio.servlet;

import br.com.sonner.estagio.connection.Conn;
import br.com.sonner.estagio.dao.EstadoDAOImpl;
import br.com.sonner.estagio.dao.api.EstadoDAO;
import br.com.sonner.estagio.model.Estado;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

@WebServlet("/estado")
public class EstadoServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        Connection connection1 = Conn.getConnection();

        Estado estado = new Estado("GOIAS", "GO");
        EstadoDAO estadoDAO = new EstadoDAOImpl();
        estadoDAO.save(estado);
    }
}
