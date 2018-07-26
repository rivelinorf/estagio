package br.com.sonner.estagio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sonner.estagio.connection.Conn;
import br.com.sonner.estagio.dao.api.BairroDAO;
import br.com.sonner.estagio.dao.api.CidadeDAO;
import br.com.sonner.estagio.model.Bairro;
import br.com.sonner.estagio.model.Cidade;

public class BairroDAOImpl implements BairroDAO {
	private Connection connection;

	public BairroDAOImpl() {
		this.connection = Conn.getConnection();
	}

	@Override
	public void save(Bairro bairro) {
		String sql = "insert into bairro (nome, bairro_cidade_fk) values (?, ?)";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, bairro.getNome());
			stmt.setLong(2, bairro.getCidade().getId());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*@Override
	public List<Bairro> getAll() {
		try {
			List<Bairro> bairros = new ArrayList<Bairro>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from bairro");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				
				CidadeDAOImpl cDAO = new CidadeDAOImpl();
				Cidade c = cDAO.getOne(rs.getLong("bairro_cidade_fk"));
				
				Bairro b = new Bairro(rs.getString("nome"), c);
				b.setNome(rs.getString("nome"));
				b.getCidade().setId(rs.getLong("bairro_cidade_fk"));
				
				bairros.add(b);
			}
			
			rs.close();
			stmt.close();
			
			return bairros;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}


	}*/

	@Override
	public void update(Bairro bairro) {
		String sql = "update bairro set nome = ? where id = ?";
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, bairro.getNome());
			stmt.setLong(2, bairro.getId());

			stmt.execute();

			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void delete(Long id) {
		String sql = "delete from bairro where id = ?";
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setLong(1, id);
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public List<Bairro> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bairro getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/*@Override
	public Bairro getOne(Long id) {
		try {
			PreparedStatement stmt = this.connection.prepareStatement("select * from bairro where id = ?");
			try {stmt.setLong(1, id);
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

			ResultSet rs = stmt.executeQuery();
			
			CidadeDAO cidadeDAO = new CidadeDAO;
			Cidade c = cidadeDAO.getOne(rs.getLong("bairro_cidade_fk"));
			Bairro b = new Bairro(rs.getString("nome"), c);

			if (rs.first()) {
				b.setNome(rs.getString("nome"));
				b.getCidade().setId(rs.getLong("bairro_cidade_fk"));
			}
			
			rs.close();
			stmt.close();
			
			return b;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}*/
}