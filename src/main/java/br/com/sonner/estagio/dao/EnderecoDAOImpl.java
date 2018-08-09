package br.com.sonner.estagio.dao;

import br.com.sonner.estagio.connection.Conn;
import br.com.sonner.estagio.dao.api.BairroDAO;
import br.com.sonner.estagio.dao.api.EnderecoDAO;
import br.com.sonner.estagio.dao.api.LogradouroDAO;
import br.com.sonner.estagio.model.Bairro;
import br.com.sonner.estagio.model.Endereco;
import br.com.sonner.estagio.model.Logradouro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDAOImpl implements EnderecoDAO {
    private Connection connection;
    public static EnderecoDAOImpl ENDERECO_DAO;

    public EnderecoDAOImpl() {
        this.connection = Conn.getConnection();
    }

    public static EnderecoDAOImpl getInstance() {
        if (ENDERECO_DAO == null) {
            ENDERECO_DAO = new EnderecoDAOImpl();
        }
        return ENDERECO_DAO;
    }

    @Override
    public void save(Endereco endereco) {
        String sql = "insert into endereco (numero, cep, complemento, endereco_logradouro_fk, endereco_bairro_fk) values (?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, endereco.getNumero());
            stmt.setString(2, endereco.getCep());
            stmt.setString(3, endereco.getComplemento());
            stmt.setLong(4, endereco.getLogradouro().getId());
            stmt.setLong(5, endereco.getBairro().getId());

            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Endereco> getAll() {
        try {
            List<Endereco> enderecos = new ArrayList<Endereco>();
            PreparedStatement stmt = this.connection.prepareStatement("select * from endereco");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                BairroDAOImpl bDAO = BairroDAOImpl.getInstance();
                Bairro b = bDAO.getOne(rs.getLong("endereco_bairro_fk"));

                LogradouroDAO lDAO = LogradouroDAOImpl.getIntance();
                Logradouro l = lDAO.getOne(rs.getLong("endereco_logradouro_fk"));

                Endereco e = new Endereco(rs.getInt("numero"), rs.getString("cep"), rs.getString("complemento"), b, l);
                e.setId(rs.getLong("id"));
                e.setNumero(rs.getInt("numero"));
                e.setCep(rs.getString("cep"));
                e.setComplemento(rs.getString("complemento"));
                e.getBairro().setId(rs.getLong("endereco_bairro_fk"));
                e.getLogradouro().setId(rs.getLong("endereco_logradouro_fk"));

                enderecos.add(e);
            }

            rs.close();
            stmt.close();

            return enderecos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Endereco endereco) {
        String sql = "update endereco set numero = ?, cep = ?, complemento = ?,endereco_bairro_fk = ?, endereco_logradouro_fk = ?  where id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setInt(1, endereco.getNumero());
            stmt.setString(2, endereco.getCep());
            stmt.setString(3, endereco.getComplemento());
            stmt.setLong(4, endereco.getBairro().getId());
            stmt.setLong(5, endereco.getLogradouro().getId());
            stmt.setLong(6, endereco.getId());

            stmt.execute();

            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "delete from endereco where id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setLong(1, id);

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Endereco getOne(Long id) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement("select * from endereco where id = ?");
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            LogradouroDAO lDAO = LogradouroDAOImpl.getIntance();

            BairroDAOImpl bDAO = BairroDAOImpl.getInstance();

            Endereco e = null;

            if (rs.first()) {
            	e = new Endereco();
                e.setId(rs.getLong("id"));
                e.setNumero(rs.getInt("numero"));
                e.setCep(rs.getString("cep"));
                e.setComplemento(rs.getString("complemento"));
                e.setBairro(bDAO.getOne(rs.getLong("endereco_bairro_fk")));
                e.setLogradouro(lDAO.getOne(rs.getLong("endereco_logradouro_fk")));
            }
            rs.close();
            stmt.close();

            return e;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

	@Override
	public List<Endereco> pesquisaEndereco(String cep) {
        try {
            List<Endereco> enderecos = new ArrayList<Endereco>();
            PreparedStatement stmt = this.connection.prepareStatement("select * from endereco where cep=?");
            
            stmt.setString(1, cep);
            
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                BairroDAOImpl bDAO = BairroDAOImpl.getInstance();
                Bairro b = bDAO.getOne(rs.getLong("endereco_bairro_fk"));

                LogradouroDAO lDAO = LogradouroDAOImpl.getIntance();
                Logradouro l = lDAO.getOne(rs.getLong("endereco_logradouro_fk"));

                Endereco e = new Endereco();
                e.setId(rs.getLong("id"));
                e.setNumero(rs.getInt("numero"));
                e.setCep(rs.getString("cep"));
                e.setComplemento(rs.getString("complemento"));
                e.getBairro().setId(rs.getLong("endereco_bairro_fk"));
                e.getLogradouro().setId(rs.getLong("endereco_logradouro_fk"));

                enderecos.add(e);
            }

            rs.close();
            stmt.close();

            return enderecos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }		
		
	}

}
