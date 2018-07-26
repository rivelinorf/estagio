package br.com.sonner.estagio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.sonner.estagio.connection.Conn;
import br.com.sonner.estagio.dao.api.LogradouroDAO;
import br.com.sonner.estagio.model.Logradouro;

public class LogradouroDAOImpl implements LogradouroDAO {

	private Connection connection;

	public LogradouroDAOImpl() {
		this.connection = Conn.getConnection();
	}

	@Override
	public void save(Logradouro Logradouro) {
		try {
			PreparedStatement stmt = connection.prepareStatement("insert into logradouro (nome,tipoLogradouro) values (?,?)");

			stmt.setString(1, Logradouro.getNome());
			//stmt.setString(2,Logradouro.getTipologradouro());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Logradouro> get(Logradouro Logradouro) {
		try {
			List<Logradouro> logradouros = new ArrayList<Logradouro>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from logradouro");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				br.com.sonner.estagio.model.Logradouro logradouro = new Logradouro("nome", null);
				logradouro.setNome(rs.getString("nome"));
				logradouros.add(logradouro);
			}

			rs.close();
			stmt.close();

			return logradouros;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(Logradouro Logradouro) {
		try {
			PreparedStatement stmt = this.connection.prepareStatement("update Logradouro set nome = ? where id = ?");
			stmt.setString(1, Logradouro.getNome());
			stmt.setLong(2, Logradouro.getId());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(Logradouro Logradouro) {
		try {
			PreparedStatement stmt = this.connection.prepareStatement("delete from logradouro where id=?");
			stmt.setLong(1, Logradouro.getId());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
