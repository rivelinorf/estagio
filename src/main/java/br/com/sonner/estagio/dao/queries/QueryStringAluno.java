package br.com.sonner.estagio.dao.queries;

public class QueryStringAluno {

    private String sql;

    public String getSql() {
        return sql;
    }

    public QueryStringAluno(QueryStringAluno.Builder builder) {
        this.sql = builder.sql;
    }

    public static class Builder {
        private String sql = "SELECT a FROM ";

        public Builder table(String table) {
            if (table != null) {
                this.sql += table + " as a WHERE 1=1 ";
            }

            return this;
        }

        public Builder nome(String nome) {
            if (nome != "" && nome != null) {
                this.sql += "and a.pessoa.nome = '" + nome + "' ";
            }

            return this;
        }

        public Builder sexo(String sexo) {
            if (sexo != "" && sexo != null) {
                this.sql += "and a.pessoa.sexo = '" + sexo + "' ";
            }
            return this;

        }

        public Builder matricula(String matricula) {
            if (matricula != null) {
                this.sql += "and a.matricula = '" + matricula + "' ";
            }
            return this;

        }


        public Builder nomeLike(String nome) {
            if (nome != "" && nome != null) {
                this.sql += "and UPPER(a.pessoa.nome) like '" + nome.toUpperCase() + "%' ";
            }


            return this;
        }

        public Builder matriculaLike(String matricula) {
            if (matricula != "" && matricula != null) {
                this.sql += "and a.pessoa.matricula like '" + matricula + "%' ";
            }
            return this;

        }


        public QueryStringAluno build() {
            return new QueryStringAluno(this);
        }
    }
}
