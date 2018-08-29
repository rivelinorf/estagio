package br.com.sonner.estagio.dao.queries;

public class QueryStringTipologradouro {
    private String sql = "SELECT * FROM tipoLogradouro WHERE 1 ";

    public String getSql() {
        return sql;
    }

    public QueryStringTipologradouro(Builder builder) {
        this.sql = builder.sql;
    }

    public static class Builder {
        private String sql = "SELECT * FROM tipoLogradouro WHERE 1 ";

        public Builder tipologradouro(String nome) {
            if (nome != "") {
                this.sql += "and nome LIKE '" + nome + "%' ";
            }
            return this;
        }

        public QueryStringTipologradouro build() {
            return new QueryStringTipologradouro(this);
        }

    }
}
