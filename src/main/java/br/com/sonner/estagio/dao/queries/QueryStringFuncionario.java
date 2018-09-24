package br.com.sonner.estagio.dao.queries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QueryStringFuncionario {
    private String sql;

    public String getSql() {
        return sql;
    }

    public QueryStringFuncionario(Builder builder) {
        this.sql = builder.sql;
    }

    public static class Builder {
        private String sql = "SELECT e FROM ";

        public Builder table(String table) {
            if (table != null) {
                this.sql += table +" as e WHERE 1=1 ";
            }

            return this;
        }

        public Builder nome(String nome) {
            if (nome != "" && nome != null) {
                this.sql += "and e.funcionario.pessoa.nome = '" + nome + "' ";
            }

            return this;
        }

        public Builder admissao(String admissao) throws ParseException {
            if (admissao != null) {
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(admissao);
                this.sql += "and e.funcionario.admissao = '" + date + "' ";
            }

            return this;
        }

        public Builder cpf(String cpf) {
            if (cpf != null && cpf != "") {
                this.sql += "and e.funcionario.pessoa.cpf = '" + cpf + "' ";
            }

            return this;
        }

        public Builder escola(Long id) {
            if (id != null) {
                this.sql += "and e.funcionario.escola = '" + id + "' ";
            }

            return this;
        }

        public Builder nomeLike(String nome) {
            if (nome != "" && nome != null) {
                this.sql += "and UPPER(e.funcionario.pessoa.nome) like '" + nome.toUpperCase() + "%' ";
            }

            return this;
        }

        /*public Builder admissaoLike(Date admissao) {
            if (admissao != null) {
                this.sql += "and UPPER(e.nome) like '" + admissao.toUpperCase() + "%' ";
            }

            return this;
        }*/

        public QueryStringFuncionario build() {
            return new QueryStringFuncionario(this);
        }
    }
}
