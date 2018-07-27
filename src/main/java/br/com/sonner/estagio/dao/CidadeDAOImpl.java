package br.com.sonner.estagio.dao;

import br.com.sonner.estagio.connection.Conn;
import br.com.sonner.estagio.dao.api.CidadeDAO;
import br.com.sonner.estagio.model.Cidade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CidadeDAOImpl implements CidadeDAO {
    private Connection connection;

    public CidadeDAOImpl() {
        this.connection = Conn.getConnection();
    }

    @Override
    public void save(Cidade cidade) {
        String sql = "insert into cidade (nome, codigo, cep, cidade_estado_fk) values (?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, cidade.getNome());
            statement.setString(2, cidade.getCod());
            statement.setString(3, cidade.getCep());
            statement.setLong(4, cidade.getEstado().getId());

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Cidade> getAll() {
        return null;
    }

    @Override
    public Cidade getOne(long id) {
        return null;
    }

    @Override
    public void update(Cidade cidade) {

    }

    @Override
    public void delete(long id) {

    }
}
