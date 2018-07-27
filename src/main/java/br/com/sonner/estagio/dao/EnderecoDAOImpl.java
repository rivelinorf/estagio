package br.com.sonner.estagio.dao;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.sonner.estagio.connection.Conn;
import br.com.sonner.estagio.dao.api.EnderecoDAO;
import br.com.sonner.estagio.model.Bairro;
import br.com.sonner.estagio.model.Endereco;
import br.com.sonner.estagio.model.Logradouro;

public class EnderecoDAOImpl implements EnderecoDAO {
	private Connection connection;

	public EnderecoDAOImpl() {
		this.connection = Conn.getConnection();
	}

	@Override
	public void save(Endereco endereco) {
		String sql = "insert into endereco (numero, cep, endereco_logradouro_fk, endereco_bairro_fk) values (?, ?, ?, ?)";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setInt(1, endereco.getNumero());
			stmt.setString(2, endereco.getCep());
			stmt.setLong(3, endereco.getLogradouro().getId());
			stmt.setLong(4, endereco.getBairro().getId());

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

				BairroDAOImpl bDAO = new BairroDAOImpl();
				Bairro b = bDAO.getOne(rs.getLong("endereco_bairro_fk"));

				LogradouroDAOImpl lDAO = new LogradouroDAOImpl();
				Logradouro l = lDAO.getOne(rs.getLong("endereco_logradouro_fk"));

				Endereco e = new Endereco(rs.getInt("numero"), rs.getString("cep"), b, l);
				e.setNumero(rs.getInt("numero"));
				e.setCep(rs.getString("cep"));
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
		String sql = "update endereco set numero = ?, cep = ? where id = ?";
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, endereco.getNumero());
			stmt.setString(2, endereco.getCep());
			stmt.setLong(3, endereco.getId());

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

			LogradouroDAOImpl lDAO = new LogradouroDAOImpl();
			Logradouro l = lDAO.getOne(rs.getLong("endereco_logradouro_fk"));

			BairroDAOImpl bDAO = new BairroDAOImpl();
			Bairro b = bDAO.getOne(rs.getLong("endereco_bairro_fk"));

			Endereco e = new Endereco(rs.getInt("numero"), rs.getString("cep"), b, l);

			if (rs.first()) {
				e.setNumero(rs.getInt("numero"));
				e.setCep(rs.getString("cep"));
				e.getBairro().setId(rs.getInt("endereco_bairro_fk"));
				e.getLogradouro().setId(rs.getInt("endereco_logradouro_fk"));
			}
			rs.close();
			stmt.close();

			return e;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
