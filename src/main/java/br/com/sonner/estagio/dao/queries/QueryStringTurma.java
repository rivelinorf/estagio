package br.com.sonner.estagio.dao.queries;

public class QueryStringTurma {

    private String sql = "SELECT t FROM Turma as t as WHERE 1=1 ";

    public String getSql() {
        return sql;
    }

    public QueryStringTurma(Builder builder) {
        this.sql = builder.sql;
    }

    public static class Builder {
        private String sql = "SELECT t FROM Turma as t WHERE 1=1 ";

        public Builder turma(String nome) {
            if (nome != "" && nome != null) {
                this.sql += "and t.nome = '" + nome + "' ";
            }

            return this;
        }


        public Builder escola(Long escola) {
            if (escola != null) {
                this.sql += "and t.escola.id = '" + escola + "' ";
            }

            return this;
        }

        public Builder turmaLike(String nome) {
            if (nome != "" && nome != null) {
                this.sql += "and UPPER(t.nome) like '" + nome.toUpperCase() + "%' ";
            }

            return this;
        }


        public QueryStringTurma build() {
            return new QueryStringTurma(this);
        }
    }
}
