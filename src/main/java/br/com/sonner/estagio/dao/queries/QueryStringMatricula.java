package br.com.sonner.estagio.dao.queries;

public class QueryStringMatricula {
    private String sql = "SELECT m FROM Matricula as m WHERE 1=1 ";

    public String getSql() {
        return sql;
    }


    public QueryStringMatricula(Builder builder) {
        this.sql = builder.sql;
    }

    public static class Builder {
        private String sql = "SELECT m FROM Matricula as m WHERE 1=1  ";


        public Builder matricula(int numero) {

            this.sql += " and m.numero = '" + numero + "'";

            return this;
        }

        public QueryStringMatricula build() {
            return new QueryStringMatricula(this);
        }
    }
}
