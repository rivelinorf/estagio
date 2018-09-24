package br.com.sonner.estagio.dao.queries;

public class QueryStringAluno {

    private String sql = "From Aluno ";

    public String getSql() {
        return sql;
    }

    public QueryStringAluno(QueryStringAluno.Builder builder) {
        this.sql = builder.sql;
    }

    public static class Builder {
        private String sql = "SELECT a FROM Aluno as a WHERE 1=1 ";

        public Builder nome(String nome) {
            if (nome != "" && nome != null) {
                this.sql += "and a.pessoa.nome = '" + nome + "' ";
            }

            return this;
        }


        public Builder nomeLike(String nome) {
            if (nome != "" && nome != null) {
                this.sql += "and UPPER(a.pessoa.nome) like '" + nome.toUpperCase() + "%' ";
            }

            return this;
        }

        /*public Builder admissaoLike(Date admissao) {
            if (admissao != null) {
                this.sql += "and UPPER(e.nome) like '" + admissao.toUpperCase() + "%' ";
            }

            return this;
        }*/

        public QueryStringAluno build() {
            return new QueryStringAluno(this);
        }
    }
}
