package br.com.sonner.estagio.dao;

public class TokenDAOImpl {
    /*private Connection connection;
    private static TokenDAO TOKEN_DAO;

    private TokenDAOImpl() {
        this.connection = Conn.getConnection();
    }

    public static TokenDAO getInstance() {
        if (TOKEN_DAO == null) {
            TOKEN_DAO = new TokenDAOImpl();
        }

        return TOKEN_DAO;
    }

    @Override
    public void save(Usuario usuario, String token) {
        String sql = "insert into tokens (usuario_id, usuario, email, token) values (?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, usuario.getId());
            statement.setString(2, usuario.getUsuario());
            statement.setString(3, usuario.getEmail());
            statement.setString(4, token);

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Usuario getUser(String token) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement("select * from tokens where token=?");
            stmt.setString(1, token);

            ResultSet resultSet = stmt.executeQuery();

            Usuario usuario = null;

            if (resultSet.first()) {
                usuario = new Usuario();

                usuario.setId(resultSet.getLong("usuario_id"));
                usuario.setUsuario(resultSet.getString("usuario"));
                usuario.setEmail(resultSet.getString("email"));

                stmt.close();
            }

            return usuario;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }*/
}
