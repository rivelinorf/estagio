package br.com.sonner.estagio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sonner.estagio.connection.Conn;
import br.com.sonner.estagio.dao.api.CidadeDAO;
import br.com.sonner.estagio.dao.api.EstadoDAO;
import br.com.sonner.estagio.dao.queries.QueryStringCidade;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.vos.CidadeFiltroVO;

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
		String sql = "select * from cidade where id=?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, id);

			ResultSet resultSet = statement.executeQuery();
			EstadoDAO estadoDAO = EstadoDAOImpl.getInstance();

			Cidade aux = null;

			if (resultSet.first()) {
				aux = new Cidade();

				aux.setId(resultSet.getLong("id"));
				aux.setNome(resultSet.getString("nome"));
				aux.setCep(resultSet.getString("cep"));
				aux.setCod(resultSet.getString("codigo"));
				aux.setEstado(estadoDAO.getOne(resultSet.getLong("cidade_estado_fk")));

				statement.close();
			}

			return aux;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void update(Cidade cidade) {
		String sql = "update cidade set nome=?, codigo=?, cep=?, cidade_estado_fk=? where id=?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, cidade.getNome());
			statement.setString(2, cidade.getCod());
			statement.setString(3, cidade.getCep());
			statement.setLong(4, cidade.getEstado().getId());
			statement.setLong(5, cidade.getId());

			statement.execute();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(long id) {
		try {
			PreparedStatement statement = connection.prepareStatement("delete from cidade where id=?");
			statement.setLong(1, id);
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Cidade> pesquisaCidade(CidadeFiltroVO vo) {
		try {
			QueryStringCidade queryString = new QueryStringCidade.Builder().cidade(vo.getNome()).cep(vo.getCep())
					.codigo(vo.getCod()).estado(vo.getEstado()).build();

			PreparedStatement statement = connection.prepareStatement(queryString.getSql());
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


}
