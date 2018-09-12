package br.com.sonner.estagio.dao;

import br.com.sonner.estagio.connection.Conn;
import br.com.sonner.estagio.controller.CidadeControllerImpl;
import br.com.sonner.estagio.controller.TipoLogradouroControllerImpl;
import br.com.sonner.estagio.controller.api.CidadeController;
import br.com.sonner.estagio.controller.api.TipoLogradouroController;
import br.com.sonner.estagio.dao.api.CidadeDAO;
import br.com.sonner.estagio.dao.api.LogradouroDAO;
import br.com.sonner.estagio.dao.api.TipoLogradouroDAO;
import br.com.sonner.estagio.dao.queries.QueryStringLogradouro;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.model.Logradouro;
import br.com.sonner.estagio.model.TipoLogradouro;
import br.com.sonner.estagio.vos.LogradouroFiltroVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LogradouroDAOImpl implements LogradouroDAO {

    private Connection connection;
    private static LogradouroDAO LOGRADOURO_DAO;

    public LogradouroDAOImpl() {
        this.connection = Conn.getConnection();
    }

    public static LogradouroDAO getIntance() {
        if (LOGRADOURO_DAO == null) {
            LOGRADOURO_DAO = new LogradouroDAOImpl();
        }
        return LOGRADOURO_DAO;
    }

    @Override
    public Logradouro save(Logradouro logradouro) {
        String sql = "insert into logradouro (nome,logradouro_cidade_fk,logradouro_tipo_fk) values (?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, logradouro.getNome());
            stmt.setLong(2, logradouro.getCidade().getId());
            stmt.setLong(3, logradouro.getTipologradouro().getId());

            stmt.execute();
            stmt.close();

            return logradouro;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }


    @Override
    public List<Logradouro> getAll() {
        try {
            PreparedStatement stmt = this.connection.prepareStatement("select * from logradouro");
            ResultSet resultSet = stmt.executeQuery();

            List<Logradouro> logradouros = new ArrayList<>();

            while (resultSet.next()) {
                CidadeController cidadeController = new CidadeControllerImpl();
                TipoLogradouroController tipoLogradouroController = new TipoLogradouroControllerImpl();

                TipoLogradouro tipologradouro = tipoLogradouroController
                        .getOne(resultSet.getLong("logradouro_tipo_fk"));
                Cidade cidade = cidadeController.getOne(resultSet.getLong("logradouro_cidade_fk"));

                Logradouro logradouro = new Logradouro(resultSet.getString("nome"), tipologradouro, cidade);
                logradouro.setId(resultSet.getLong("id"));
                logradouro.setNome(resultSet.getString("nome"));
                logradouro.getTipologradouro().setId(resultSet.getLong("logradouro_tipo_fk"));
                logradouro.getCidade().setId(resultSet.getLong("logradouro_cidade_fk"));

                logradouros.add(logradouro);
            }

            return logradouros;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(Logradouro logradouro) {
        String sql = "UPDATE logradouro SET nome=?, logradouro_cidade_fk=?, logradouro_tipo_fk=? where id = ? ";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, logradouro.getNome());
            stmt.setLong(2, logradouro.getCidade().getId());
            stmt.setLong(3, logradouro.getTipologradouro().getId());
            stmt.setLong(4, logradouro.getId());

            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void delete(Long id) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement("delete from logradouro where id=?");
            stmt.setLong(1, id);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Logradouro getOne(Long id) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement("select * from logradouro where id = ?");
            stmt.setLong(1, id);

            ResultSet resultSet = stmt.executeQuery();
            TipoLogradouroDAO tipoLogradouroDAO = TipoLogradouroDAOImpl.getInstance();
            CidadeDAO cidadeDAO = CidadeDAOImpl.getInstance();

            Logradouro logradouro = null;

            if (resultSet.first()) {
                logradouro = new Logradouro();
                logradouro.setId(resultSet.getLong("id"));
                logradouro.setNome(resultSet.getString("nome"));
                logradouro.setCidade(cidadeDAO.getOne(resultSet.getLong("logradouro_cidade_fk")));
                logradouro.setTipologradouro(tipoLogradouroDAO.getOne(resultSet.getLong("logradouro_tipo_fk")));

                stmt.close();
            }

            return logradouro;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Logradouro> pesquisaLogradouro(LogradouroFiltroVO vo) {
        try {
            QueryStringLogradouro queryString = new QueryStringLogradouro.Builder()
                    .logradouro(vo.getNome())
                    .tipologradouro(vo.getTipologradouro())
                    .cidade(vo.getCidade())
                    .build();

            PreparedStatement preparedStatement = connection.prepareStatement(queryString.getSql());
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Logradouro> logradouros = new ArrayList<>();

            while (resultSet.next()) {
                Logradouro aux = new Logradouro();

                aux.setId(resultSet.getLong("id"));
                aux.setNome(resultSet.getString("nome"));
                aux.setTipologradouro(TipoLogradouroDAOImpl.getInstance().getOne(resultSet.getLong("logradouro_tipo_fk")));
                aux.setCidade(CidadeDAOImpl.getInstance().getOne(resultSet.getLong("logradouro_cidade_fk")));

                logradouros.add(aux);
            }

            return logradouros;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Logradouro> pesquisaLogradouroLike(LogradouroFiltroVO vo) {
        try {
            QueryStringLogradouro queryString = new QueryStringLogradouro.Builder()
                    .logradouroLike(vo.getNome())
                    .tipologradouro(vo.getTipologradouro())
                    .cidade(vo.getCidade())
                    .build();
            PreparedStatement preparedStatement = connection.prepareStatement(queryString.getSql());
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Logradouro> logradouros = new ArrayList<>();

            while (resultSet.next()) {
                Logradouro aux = new Logradouro();

                aux.setId(resultSet.getLong("id"));
                aux.setNome(resultSet.getString("nome"));
                aux.setTipologradouro(TipoLogradouroDAOImpl.getInstance().getOne(resultSet.getLong("logradouro_tipo_fk")));
                aux.setCidade(CidadeDAOImpl.getInstance().getOne(resultSet.getLong("logradouro_cidade_fk")));

                logradouros.add(aux);
            }

            return logradouros;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}