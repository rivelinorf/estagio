package br.com.sonner.estagio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.sonner.estagio.connection.Conn;
import br.com.sonner.estagio.dao.api.LogradouroDAO;
import br.com.sonner.estagio.model.Bairro;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.model.Logradouro;
import br.com.sonner.estagio.model.TipoLogradouro;

public class LogradouroDAOImpl implements LogradouroDAO {

	private Connection connection;

	public LogradouroDAOImpl() {
		this.connection = Conn.getConnection();
	}

	@Override
	public void save(Logradouro logradouro) {
		try {
			PreparedStatement stmt = connection.prepareStatement("insert into logradouro (nome,cidade,tipoLogradouro) values (?,?,?)");

			stmt.setString(1, logradouro.getNome());
			stmt.setLong (2, logradouro.getCidade().getId());
			stmt.setLong(3,logradouro.getTipologradouro().getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Logradouro> getAll() {
		try {
			List<Logradouro> logradouros = new ArrayList<Logradouro>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from logradouro");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				br.com.sonner.estagio.model.Logradouro logradouro = new Logradouro("nome", null,null);
				logradouro.setNome(rs.getString("nome"));
				//logradouro.setTipologradouro(rs.getLong(tipologradouro));
				//logradouro.setCidade(rs.getString(cidade));
				
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
	public void update(Logradouro logradouro) {
		try {
			PreparedStatement stmt = this.connection.prepareStatement("update Logradouro set nome = ? where id = ?");
			stmt.setString(1, logradouro.getNome());
			stmt.setLong(2, logradouro.getId());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(Long id) {
		try {
			PreparedStatement stmt = this.connection.prepareStatement("delete from logradouro where id=?");
			stmt.setLong(1, id);
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Logradouro getOne(Long id) {
		try {
			PreparedStatement stmt = this.connection.prepareStatement("select * from bairro where id = ?");
			try {stmt.setLong(1, id);
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

			ResultSet rs = stmt.executeQuery();
			
			LogradouroDAO logradouroDAO = new LogradouroDAO();
			Logradouro l = logradouroDAO.getOne(rs.getLong("logradouro_cidade_fk"));

			while (rs.next()) {
				tl.setNome(rs.getString("nome"));
				l.getTipologradouro().setId(rs.getLong("logradouro_tipo_fk"));
			}
			
			rs.close();
			stmt.close();
			
			return b;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}

}
