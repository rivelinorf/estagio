package br.com.sonner.estagio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.sonner.estagio.connection.Conn;
import br.com.sonner.estagio.dao.api.TipoLogradouroDAO;
import br.com.sonner.estagio.model.TipoLogradouro;

public class TipoLogradouroDAOImpl implements TipoLogradouroDAO {
	private Connection connection;

	public TipoLogradouroDAOImpl() {
		this.connection = Conn.getConnection();
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
	public List<TipoLogradouro> get(TipoLogradouro tipoLogradouro) {
		try {
			List<TipoLogradouro> tipoLogradouros = new ArrayList<TipoLogradouro>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from tipoLogradouro");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				TipoLogradouro tipoLogradouro2 = new TipoLogradouro("nome");
				tipoLogradouro2.setNome(rs.getString("nome"));
				tipoLogradouros.add(tipoLogradouro2);
			}

			rs.close();
			stmt.close();

			return tipoLogradouros;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(TipoLogradouro tipoLogradouro) {
		String sql = "update tipoLogradouro set nome = ? where id = ?";
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, tipoLogradouro.getNome());
			stmt.setLong(2, tipoLogradouro.getId());

			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(TipoLogradouro tipoLogradouro) {
		String sql = "delete from tipoLogradouro where id=?";
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setLong(1, tipoLogradouro.getId());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
