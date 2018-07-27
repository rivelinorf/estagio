package br.com.sonner.estagio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.sonner.estagio.connection.Conn;
import br.com.sonner.estagio.dao.api.LogradouroDAO;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.model.Logradouro;
import br.com.sonner.estagio.model.TipoLogradouro;

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
	public void save(Logradouro logradouro) {
		try {
			PreparedStatement stmt = connection.prepareStatement(
					"insert into logradouro (nome,logradouro_cidade_fk,logradouro_tipo_fk) values (?,?,?)");

			stmt.setString(1, logradouro.getNome());
			stmt.setLong(2, logradouro.getCidade().getId());
			stmt.setLong(3, logradouro.getTipologradouro().getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Logradouro> getAll() {
		try {
			PreparedStatement stmt = this.connection.prepareStatement("select * from logradouro");
			ResultSet resultSet = stmt.executeQuery();

			List<Logradouro> logradouros = new ArrayList<>();

			while (resultSet.next()) {
				// tipo logradouro getONE e salva na variavel "t"
				TipoLogradouro t = null;

				// Cidade getONE e salva na variavel "c"
				Cidade c = null;

				Logradouro l = new Logradouro(resultSet.getString("nome"), t, c);
				l.setNome(resultSet.getString("nome"));
				l.getTipologradouro().setId(resultSet.getLong("logradouro_tipo_fk"));
				l.getCidade().setId(resultSet.getLong("logradouro_cidade_fk"));

				logradouros.add(l);
			}

			resultSet.close();
			stmt.close();

			return logradouros;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(Logradouro logradouro) {
		try {
			PreparedStatement stmt = this.connection.prepareStatement("update Logradouro set nome = ? where id = ?");
			stmt.setString(1, logradouro.getNome());
			stmt.setLong(2, logradouro.getId());

			stmt.execute();

			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(Long id) {
		try {
			PreparedStatement stmt = this.connection.prepareStatement("delete from logradouro where id=?");
			stmt.setLong(1, id);
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Logradouro getOne(Long id) {
		try {
			PreparedStatement stmt = this.connection.prepareStatement("select * from logradouro where id = ?");
			stmt.setLong(1, id);

			ResultSet rs = stmt.executeQuery();

			// tipo logradouro getONE e salva na variavel "t"
			TipoLogradouro t = null;

			// Cidade getONE e salva na variavel "c"
			Cidade c = null;

			Logradouro logradouro = new Logradouro(rs.getString("nome"), t, c);

			if (rs.first()) {
				logradouro.setNome(rs.getString("nome"));
				logradouro.getCidade().setId(rs.getLong("logradouro_cidade_fk"));
				logradouro.getTipologradouro().setId(rs.getLong("logradouro_tipo_fk"));
			}

			return logradouro;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
