package br.com.sonner.estagio.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {
    private static Connection CONNECTION;

    private Conn() {
    }

    public Connection getConnection() {
        try {
            if (CONNECTION == null) {
                CONNECTION = DriverManager.getConnection("jdbc:mysql://localhost/javajdbc", "root", "root");
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
    }
}
