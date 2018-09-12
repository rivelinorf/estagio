package br.com.sonner.estagio.dao.queries;

public class QueryStringBairro {
    private String sql = "SELECT b FROM bairro as b WHERE 1=1 ";

    public String getSql() {
        return sql;
    }

    public QueryStringBairro(Builder builder) {
        this.sql = builder.sql;
    }

    public static class Builder {
        private String sql = "SELECT b FROM bairro as b WHERE 1=1 ";

        public Builder bairro(String nome) {
            if (nome != "" && nome != null) {
                this.sql += "and b.nome = '" + nome + "'";
            }
            return this;
        }

        public Builder cidade(Long cidade) {
            if (cidade != null) {
                this.sql += "and b.cidade.id = '" + cidade + "'";
            }

            return this;
        }

        public Builder bairroLike(String nome) {
            if (nome != "" && nome != null) {
                this.sql += "and UPPER(b.nome) like '" + nome.toUpperCase() + "%'";
            }
            return this;
        }


        public QueryStringBairro build() {
            return new QueryStringBairro(this);
        }
    }
}
