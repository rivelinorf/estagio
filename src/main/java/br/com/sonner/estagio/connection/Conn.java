package br.com.sonner.estagio.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Conn {
    private static Connection CONNECTION;

    private Conn() {
    }

    public static Properties loadProperties() throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("config.properties");

        Properties properties = new Properties();
        properties.load(input);

        return properties;
    }

    public static Connection getConnection() {
        try {
            if (CONNECTION == null) {
                Properties properties = loadProperties();
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                CONNECTION = DriverManager.getConnection(properties.getProperty("url") + properties.getProperty("database"), properties.getProperty("user"), properties.getProperty("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return CONNECTION;
    }


    public static void main(String[] args) {
        Connection connection = Conn.getConnection();
    }
}
