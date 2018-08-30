package br.com.sonner.estagio.dao;

import br.com.sonner.estagio.connection.Conn;
import br.com.sonner.estagio.dao.api.TipoLogradouroDAO;
import br.com.sonner.estagio.dao.queries.QueryStringTipologradouro;
import br.com.sonner.estagio.model.TipoLogradouro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoLogradouroDAOImpl implements TipoLogradouroDAO {

    private Connection connection;
    private static TipoLogradouroDAO TIPOLOGRADOURO_DAO;

    public TipoLogradouroDAOImpl() {
        this.connection = Conn.getConnection();
    }

    public static TipoLogradouroDAO getInstance() {
        if (TIPOLOGRADOURO_DAO == null) {
            TIPOLOGRADOURO_DAO = new TipoLogradouroDAOImpl();
        }

        return TIPOLOGRADOURO_DAO;

    }

    @Override
    public void save(TipoLogradouro tipoLogradouro) {
        String sql = "insert into tipoLogradouro (nome) values (?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, tipoLogradouro.getNome());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TipoLogradouro> getAll() {
        try {
            List<TipoLogradouro> tipoLogradouros = new ArrayList<TipoLogradouro>();
            PreparedStatement stmt = this.connection.prepareStatement("select * from tipoLogradouro");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                TipoLogradouro tipoLogradouro2 = new TipoLogradouro("nome");
                tipoLogradouro2.setNome(rs.getString("nome"));
                tipoLogradouro2.setId(rs.getLong("id"));
                tipoLogradouros.add(tipoLogradouro2);
            }

            rs.close();
            stmt.close();

            return tipoLogradouros;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(TipoLogradouro tipoLogradouro) {
        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("update tipoLogradouro set nome = ? where id = ?");
            stmt.setString(1, tipoLogradouro.getNome());
            stmt.setLong(2, tipoLogradouro.getId());

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement("delete from tipoLogradouro where id=?");
            stmt.setLong(1, id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public TipoLogradouro getOne(Long id) {
        try {
            PreparedStatement stmt = connection.prepareStatement("select * from tipoLogradouro where id=?");
            stmt.setLong(1, id);
            ResultSet resultSet = stmt.executeQuery();

            TipoLogradouro aux = new TipoLogradouro();
            if (resultSet.first()) {

                aux.setNome(resultSet.getString("nome"));
                aux.setId(resultSet.getLong("id"));

                stmt.close();
            }

            return aux;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<TipoLogradouro> pesquisaTipoLogradouro(String nome) {

        try {
            QueryStringTipologradouro queryString = new QueryStringTipologradouro.Builder().tipologradouro(nome).build();
            PreparedStatement preparedStatement = connection.prepareStatement(queryString.getSql());

            ResultSet resultSet = preparedStatement.executeQuery();
            List<TipoLogradouro> tipologradouros = new ArrayList<>();

            while (resultSet.next()) {
                TipoLogradouro aux = new TipoLogradouro();

                aux.setId(resultSet.getLong("id"));
                aux.setNome(resultSet.getString("nome"));

                tipologradouros.add(aux);
            }

            return tipologradouros;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
