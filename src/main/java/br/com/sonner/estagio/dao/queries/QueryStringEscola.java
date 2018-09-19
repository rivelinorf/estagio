package br.com.sonner.estagio.dao.queries;

public class QueryStringEscola {
    private String sql = "SELECT e FROM Escola as e WHERE 1=1 ";

    public String getSql() {
        return sql;
    }

    public QueryStringEscola(Builder builder) {
        this.sql = builder.sql;
    }

    public static class Builder {
        private String sql = "SELECT e FROM Escola as e WHERE 1=1 ";

        public Builder bairro(String nome) {
            if (nome != "" && nome != null) {
                this.sql += "and e.nome = '" + nome + "' ";
            }
            return this;
        }

        public Builder endereco(Long endereco) {
            if (endereco != null) {
                this.sql += "and e.endereco.id = '" + endereco + "' ";
            }

            return this;
        }

        public Builder bairroLike(String nome) {
            if (nome != "" && nome != null) {
                this.sql += "and UPPER(e.nome) like '" + nome.toUpperCase() + "%' ";
            }
            return this;
        }


        public QueryStringEscola build() {
            return new QueryStringEscola(this);
        }
    }
}
