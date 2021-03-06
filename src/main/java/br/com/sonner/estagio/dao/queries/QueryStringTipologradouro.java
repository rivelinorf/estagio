package br.com.sonner.estagio.dao.queries;

public class QueryStringTipologradouro {
    private String sql = "SELECT t FROM TipoLogradouro as t WHERE 1=1 ";

    public String getSql() {
        return sql;
    }

    public QueryStringTipologradouro(Builder builder) {
        this.sql = builder.sql;
    }

    public static class Builder {
        private String sql = "SELECT t FROM TipoLogradouro as t WHERE 1=1 ";

        public Builder tipologradouro(String nome) {
            if (nome != "" && nome != null) {
                this.sql += " and t.nome = '" + nome + "' ";
            }
            return this;
        }

        public Builder tipologradouroLike(String nome) {
            if (nome != "") {
                this.sql += " and UPPER(t.nome) LIKE '" + nome.toUpperCase() + "%' ";
            }
            return this;
        }


        public QueryStringTipologradouro build() {
            return new QueryStringTipologradouro(this);
        }

    }
}
