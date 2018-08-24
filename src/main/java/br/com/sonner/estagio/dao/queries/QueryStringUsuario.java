
package br.com.sonner.estagio.dao.queries;

public class QueryStringUsuario {
    private String sql = "SELECT * FROM usuario WHERE 1 ";

    public String getSql() {
        return sql;
    }

    public QueryStringUsuario(Builder builder) {
        this.sql = builder.sql;
    }

    public static class Builder {
        private String sql = "SELECT * FROM usuario WHERE 1 ";

        public Builder usuario(String usuario, String email) {
            if (usuario != "") {
                this.sql += "  and usuario = '" + usuario + "'";
            }
            if (email != "") {
                this.sql += " and email = '" + email + "'";
            }

            return this;
        }


        public QueryStringUsuario build() {
            return new QueryStringUsuario(this);
        }
    }
}
