package br.com.sonner.estagio.dao.queries;

public class QueryStringLogradouro {
    private String sql = "SELECT * FROM logradouro WHERE 1 ";

    public String getSql() {
        return sql;
    }

    public QueryStringLogradouro(Builder builder) {
        this.sql = builder.sql;
    }

    public static class Builder {
        private String sql = "SELECT * FROM logradouro WHERE 1 ";

        public Builder logradouro(String nome) {
            if (nome != "" && nome != null) {
                this.sql += "and nome = '" + nome + "'";
            }
            return this;
        }

        public Builder cidade(Long cidade) {
            if (cidade != null) {
                this.sql += "and logradouro_cidade_fk = '" + cidade + "'";
            }

            return this;
        }

        public Builder tipologradouro(Long tipologradouro) {
            if (tipologradouro != null) {
                this.sql += "and logradouro_tipo_fk = '" + tipologradouro + "'";
            }

            return this;
        }

        public Builder logradouroLike(String nome) {
            if (nome != "" && nome != null) {
                this.sql += "and UPPER(nome) LIKE '" + nome.toUpperCase() + "%' ";
            }
            return this;
        }


        public QueryStringLogradouro build() {
            return new QueryStringLogradouro(this);
        }
    }
}
