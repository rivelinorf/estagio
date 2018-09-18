package br.com.sonner.estagio.dao.queries;

public class QueryStringDisciplina {
    private String sql = "SELECT d FROM Disciplina as d WHERE 1=1 ";

    public String getSql() {
        return sql;
    }

    public QueryStringDisciplina(QueryStringDisciplina.Builder builder) {
        this.sql = builder.sql;
    }

    public static class Builder {
        private String sql = "SELECT d FROM Disciplina as d WHERE 1=1 ";

        public QueryStringDisciplina.Builder disciplina(String nome) {
            if (nome != "" && nome != null) {
                this.sql += " and d.nome = '" + nome + "' ";
            }
            return this;
        }

        public QueryStringDisciplina.Builder disciplinaLike(String nome) {
            if (nome != "") {
                this.sql += " and UPPER(d.nome) LIKE '" + nome.toUpperCase() + "%' ";
            }
            return this;
        }


        public QueryStringDisciplina build() {
            return new QueryStringDisciplina(this);
        }

    }
}
