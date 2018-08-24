package br.com.sonner.estagio.dao;

import br.com.sonner.estagio.connection.Conn;
import br.com.sonner.estagio.dao.api.UsuarioDAO;
import br.com.sonner.estagio.dao.queries.QueryStringLogradouro;
import br.com.sonner.estagio.dao.queries.QueryStringUsuario;
import br.com.sonner.estagio.model.Logradouro;
import br.com.sonner.estagio.model.Usuario;
import br.com.sonner.estagio.vos.UsuarioFiltroVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Usuario> pesquisaUsuario(UsuarioFiltroVo vo) {
        try {
            QueryStringUsuario queryString = new QueryStringUsuario.Builder()
                    .usuario(vo.getUsuario(), vo.getEmail())
                    .build();

            PreparedStatement preparedStatement = connection.prepareStatement(queryString.getSql());
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Usuario> usuarios = new ArrayList<>();

            while (resultSet.next()) {
                Usuario aux = new Usuario();

                aux.setId(resultSet.getLong("id"));
                aux.setEmail(resultSet.getString("email"));
                aux.setUsuario(resultSet.getString("usuario"));
                usuarios.add(aux);
            }

            return usuarios;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

}
