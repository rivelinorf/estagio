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
	private static TipoLogradouroDAO TIPOLOGRADOURO_DAO;
	

	public TipoLogradouroDAOImpl() {
		this.connection = Conn.getConnection();
	}
	
	public static TipoLogradouroDAO getInstance() {
		if(TIPOLOGRADOURO_DAO==null) {
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
		try {
			PreparedStatement stmt = this.connection
					.prepareStatement("update tipoLogradouro set nome = ? where id = ?");
			stmt.setString(1, tipoLogradouro.getNome());
			stmt.setLong(2, tipoLogradouro.getId());

			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(Long id) {
		try {
			PreparedStatement stmt = this.connection.prepareStatement("delete from tipoLogradouro where id=?");
			stmt.setLong(1, id);
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public TipoLogradouro getOne(Long id) {

		try {
			PreparedStatement stmt = connection.prepareStatement("select from tipoLogradouro where id=?");
			stmt.setLong(1, id);
			ResultSet resultSet = stmt.executeQuery();

			TipoLogradouro temp = null;
			if (resultSet.first()) {
				temp = new TipoLogradouro(resultSet.getString("nome"));
				temp.setId(resultSet.getLong("id"));
			}
			return temp;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

}
