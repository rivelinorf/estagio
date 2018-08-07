package br.com.sonner.estagio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.sonner.estagio.connection.Conn;
import br.com.sonner.estagio.dao.api.UsuarioDAO;
import br.com.sonner.estagio.model.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {
	private Connection connection;
	public static UsuarioDAO USUARIO_DAO;
	
	private UsuarioDAOImpl() {
		this.connection = Conn.getConnection();
	}
	
	public static UsuarioDAO getInstance() {
		if (USUARIO_DAO == null){
			USUARIO_DAO = new UsuarioDAOImpl();
		}
		
		return USUARIO_DAO;
	}

	@Override
	public void save(Usuario usuario) {
		String sql = "insert into usuario (usuario, senha, nome) values (?, ?, ?)";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, usuario.getUsuario());
            statement.setString(2, usuario.getSenha());
            statement.setString(3, usuario.getNome());
			
			statement.execute();
            statement.close();
		} catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public boolean efetuaLogin(Usuario usuario) {
		String sql = "select * from usuario where usuario = ? and senha = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, usuario.getUsuario());
			statement.setString(2, usuario.getSenha());
			
			ResultSet rs = statement.executeQuery();
			
			if(rs.next()) {
				rs.close();
				statement.close();
				return true;
			}
			
			rs.close();
			statement.close();
			return false;
			
		} catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
		
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
		
	}

}
