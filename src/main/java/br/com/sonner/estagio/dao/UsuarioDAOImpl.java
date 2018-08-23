package br.com.sonner.estagio.dao;

import br.com.sonner.estagio.connection.Conn;
import br.com.sonner.estagio.dao.api.UsuarioDAO;
import br.com.sonner.estagio.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAOImpl implements UsuarioDAO {
    private Connection connection;
    public static UsuarioDAO USUARIO_DAO;

    private UsuarioDAOImpl() {
        this.connection = Conn.getConnection();
    }

    public static UsuarioDAO getInstance() {
        if (USUARIO_DAO == null) {
            USUARIO_DAO = new UsuarioDAOImpl();
        }

        return USUARIO_DAO;
    }

    @Override
    public void save(Usuario usuario) {
        String sql = "insert into usuario (usuario, senha, email) values (?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, usuario.getUsuario());
            statement.setString(2, usuario.getSenha());
            statement.setString(3, usuario.getEmail());

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Usuario efetuaLogin(Usuario usuario) {
        String sql = "select * from usuario where usuario = ? and senha = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, usuario.getUsuario());
            statement.setString(2, usuario.getSenha());

            ResultSet rs = statement.executeQuery();

            Usuario aux;
            if (rs.first()) {
                aux = new Usuario();

                aux.setId(rs.getLong("id"));
                aux.setUsuario(rs.getString("usuario"));
                aux.setSenha(rs.getString("senha"));

                rs.close();
                statement.close();

                return usuario;
            }

            rs.close();
            statement.close();
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

}
