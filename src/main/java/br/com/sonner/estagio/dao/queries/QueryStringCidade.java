package br.com.sonner.estagio.dao.queries;

public class QueryStringCidade {
    private String sql = "SELECT * FROM cidade WHERE 1 ";

    public String getSql() {
        return sql;
    }

    public QueryStringCidade(Builder builder) {
        this.sql = builder.sql;
    }

    public static class Builder {
        private String sql = "SELECT * FROM cidade WHERE 1 ";

        public Builder cidade(String nome) {
            if (nome != "" && nome != null) {
                this.sql += "and nome = '" + nome + "' ";
            }

            return this;
        }

        public Builder codigo(String abv) {
            if (abv != "" && abv != null) {
                this.sql += "and codigo = '" + abv + "' ";
            }

            return this;
        }

        public Builder cep(String cep) {
            if (cep != "" && cep != null) {
                this.sql += "and cep = '" + cep + "' ";
            }

            return this;
        }

        public Builder estado(Long estado) {
            if (estado != null) {
                this.sql += "and cidade_estado_fk = '" + estado + "' ";
            }

            return this;
        }

        public Builder cidadeLike(String nome) {
            if (nome != "" && nome != null) {
                this.sql += "and UPPER(nome) LIKE '" + nome.toUpperCase() + "%' ";
            }

            return this;
        }

        public Builder codigoLike(String abv) {
            if (abv != "" && abv != null) {
                this.sql += "and UPPER(codigo) LIKE '" + abv.toUpperCase() + "%' ";
            }

            return this;
        }

        public Builder cepLike(String cep) {
            if (cep != "" && cep != null) {
                this.sql += "and UPPER(cep) LIKE '" + cep.toUpperCase() + "%' ";
            }

            return this;
        }

        public QueryStringCidade build() {
            return new QueryStringCidade(this);
        }
    }
}
