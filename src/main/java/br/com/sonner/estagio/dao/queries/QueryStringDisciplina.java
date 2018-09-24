package br.com.sonner.estagio.dao.queries;

public class QueryStringDisciplina {
    private String sql = "SELECT d FROM Disciplina as d as WHERE 1=1 ";

    public String getSql() {
        return sql;
    }

    public QueryStringDisciplina(Builder builder) {
        this.sql = builder.sql;
    }

    public static class Builder {
        private String sql = "SELECT d FROM Disciplina as d WHERE 1=1 ";

        public Builder disciplina(String nome) {
            if (nome != "" && nome != null) {
                this.sql += "and d.nome = '" + nome + "' ";
            }

            return this;
        }


        public Builder escola(Long escola) {
            if (escola != null) {
                this.sql += "and d.escola.id = '" + escola + "' ";
            }

            return this;
        }

        public Builder disciplinaLike(String nome) {
            if (nome != "" && nome != null) {
                this.sql += "and UPPER(d.nome) like '" + nome.toUpperCase() + "%' ";
            }

            return this;
        }


        public QueryStringDisciplina build() {
            return new QueryStringDisciplina(this);
        }
    }
}
