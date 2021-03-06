package br.com.sonner.estagio.dao.queries;

public class QueryStringEstado {
    private String sql = "From Estado ";

    public String getSql() {
        return sql;
    }

    public QueryStringEstado(Builder builder) {
        this.sql = builder.sql;
    }

    public static class Builder {
        private String sql = "SELECT e FROM Estado as e WHERE 1=1 ";

        public Builder estado(String nome) {
            if (nome != "" && nome != null) {
                this.sql += "and e.nome = '" + nome + "' ";
            }

            return this;
        }

        public Builder abv(String abv) {
            if (abv != "" && abv != null) {
                this.sql += "and e.abv = '" + abv + "' ";
            }

            return this;
        }

        public Builder estadoLike(String nome) {
            if (nome != "" && nome != null) {
                this.sql += "and UPPER(e.nome) like '" + nome.toUpperCase() + "%' ";
            }

            return this;
        }

        public Builder abvLike(String abv) {
            if (abv != "" && abv != null) {
                this.sql += "and UPPER(e.nome) like '" + abv.toUpperCase() + "%' ";
            }

            return this;
        }

        public QueryStringEstado build() {
            return new QueryStringEstado(this);
        }
    }
}
