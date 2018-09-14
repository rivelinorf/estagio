package br.com.sonner.estagio.dao.queries;

public class QueryStringLogradouro {
    private String sql = "SELECT l FROM Logradouro as l WHERE 1=1 ";

    public String getSql() {
        return sql;
    }

    public QueryStringLogradouro(Builder builder) {
        this.sql = builder.sql;
    }

    public static class Builder {
        private String sql = "SELECT l FROM Logradouro as l  WHERE 1=1 ";

        public Builder logradouro(String nome) {
            if (nome != "" && nome != null) {
                this.sql += "and l.nome = '" + nome + "'";
            }
            return this;
        }

        public Builder cidade(Long cidade) {
            if (cidade != null) {
                this.sql += "and l.cidade_id = '" + cidade + "'";
            }

            return this;
        }

        public Builder tipologradouro(Long tipologradouro) {
            if (tipologradouro != null) {
                this.sql += "and l.tipologradouro_id = '" + tipologradouro + "'";
            }

            return this;
        }

        public Builder logradouroLike(String nome) {
            if (nome != "" && nome != null) {
                this.sql += "and UPPER(l.nome) LIKE '" + nome.toUpperCase() + "%' ";
            }
            return this;
        }


        public QueryStringLogradouro build() {
            return new QueryStringLogradouro(this);
        }
    }
}
