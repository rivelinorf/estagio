package br.com.sonner.estagio.connection;

import br.com.sonner.estagio.dao.EstadoDAOImpl;
import br.com.sonner.estagio.dao.api.EstadoDAO;
import br.com.sonner.estagio.model.Estado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {
    private static Connection CONNECTION;

    private Conn() {
    }

    public static Connection getConnection() {
        try {
            if (CONNECTION == null) {
                CONNECTION = DriverManager.getConnection("jdbc:mysql://localhost/sge", "root", "root");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return CONNECTION;
    }

    public static void main(String[] args) {
        Connection connection1 = new Conn().getConnection();
        Connection connection2 = new Conn().getConnection();
        System.out.println(connection1);
        System.out.println(connection2);

        EstadoDAO estadoDAO = new EstadoDAOImpl();
        Estado estado = new Estado("MINAS GERAIS", "MG");

        estadoDAO.save(estado);
    }
}
