package br.com.sonner.estagio.dao;

import br.com.sonner.estagio.connection.Conn;
import br.com.sonner.estagio.dao.api.EstadoDAO;
import br.com.sonner.estagio.model.Estado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public List<Estado> getAll() {
        try {
            PreparedStatement stmt = connection.prepareStatement("select * from estado");
            ResultSet resultSet = stmt.executeQuery();

            List<Estado> estados = new ArrayList<>();

            while(resultSet.next()) {
                Estado aux = new Estado(resultSet.getString("nome"), resultSet.getString("abv"));

                aux.setId(resultSet.getLong("id"));

                estados.add(aux);
            }

            return estados;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Estado getOne(Long id) {
        try {
            PreparedStatement stmt = connection.prepareStatement("select * from estado where id=?");
            stmt.setLong(1, id);
            ResultSet resultSet = stmt.executeQuery();

            Estado aux = null;

            if(resultSet.first()) {
                aux = new Estado(resultSet.getString("nome"), resultSet.getString("abv"));
                aux.setId(resultSet.getLong("id"));
            }

            return aux;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(Estado estado) {
        String sql = "update estado set nome=?, abv=? where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, estado.getNome());
            statement.setString(2, estado.getAbv());
            statement.setLong(3, estado.getId());

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "delete from estado where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, id);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}