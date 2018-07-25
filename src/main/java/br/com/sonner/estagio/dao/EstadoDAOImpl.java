package br.com.sonner.estagio.dao;

import br.com.sonner.estagio.connection.Conn;
import br.com.sonner.estagio.dao.api.EstadoDAO;
import br.com.sonner.estagio.model.Estado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class EstadoDAOImpl implements EstadoDAO {
    private Connection connection;

    public EstadoDAOImpl() {
        this.connection = Conn.getConnection();
    }

    @Override
    public void save(Estado estado) {
        String sql = "insert into estado (nome, abv) values (?,?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, estado.getNome());
            stmt.setString(2, estado.getAbv());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Estado> get(Estado estado) {
        return null;
    }

    @Override
    public void update(Estado estado) {
        return null;
    }

    @Override
    public void delete(Estado estado) {
        return null;
    }
}
