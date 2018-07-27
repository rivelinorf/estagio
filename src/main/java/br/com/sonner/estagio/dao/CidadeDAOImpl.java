package br.com.sonner.estagio.dao;

import br.com.sonner.estagio.connection.Conn;
import br.com.sonner.estagio.dao.api.CidadeDAO;
import br.com.sonner.estagio.model.Cidade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CidadeDAOImpl implements CidadeDAO {
    private Connection connection;
    private static CidadeDAO CIDADE_DAO;

    private CidadeDAOImpl() {
        this.connection = Conn.getConnection();
    }

    public static CidadeDAO getInstance() {
        if (CIDADE_DAO == null) {
            CIDADE_DAO = new CidadeDAOImpl();
        }

        return CIDADE_DAO;
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
        try {
            String sql = "select * from cidade";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            List<Cidade> cidades = new ArrayList<>();

            while (resultSet.next()) {
                Cidade aux = new Cidade();

                aux.setId(resultSet.getLong("id"));
                aux.setNome(resultSet.getString("nome"));
                aux.setCod(resultSet.getString("codigo"));
                aux.setCep(resultSet.getString("cep"));
                aux.setEstado(EstadoDAOImpl.getInstance().getOne(resultSet.getLong("cidade_estado_fk")));

                cidades.add(aux);
            }

            return cidades;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
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
