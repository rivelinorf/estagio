package br.com.sonner.estagio.dao.queries;

public class QueryStringSala {
    private String sql = "SELECT s FROM Sala as s WHERE 1=1 ";

    public String getSql() {
        return sql;
    }

    public QueryStringSala(QueryStringSala.Builder builder) {
        this.sql = builder.sql;
    }

    public static class Builder {
        private String sql = "SELECT s FROM Sala as s WHERE 1=1 ";

        public QueryStringSala.Builder sala(String nome) {
            if (nome != "" && nome != null) {
                this.sql += " and s.nome = '" + nome + "'";
            }
            return this;
        }

        public QueryStringSala.Builder escola(Long escola) {
            if (escola != null) {
                this.sql += " and s.escola.id = '" + escola + "' ";
            }

            return this;
        }


        public QueryStringSala.Builder salaLike(String nome) {
            if (nome != "" && nome != null) {
                this.sql += " and UPPER(s.nome) LIKE '" + nome.toUpperCase() + "%' ";
            }
            return this;
        }


        public QueryStringSala build() {
            return new QueryStringSala(this);
        }
    }
}
