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
        private String sql = "SELECT c FROM Cidade as c WHERE 1=1 ";

        public Builder cidade(String nome) {
            if (nome != "" && nome != null) {
                this.sql += "and c.nome = '" + nome + "' ";
            }

            return this;
        }

        public Builder codigo(String cod) {
            if (cod != "" && cod != null) {
                this.sql += "and c.cod = '" + cod + "' ";
            }

            return this;
        }

        public Builder cep(String cep) {
            if (cep != "" && cep != null) {
                this.sql += "and c.cep = '" + cep + "' ";
            }

            return this;
        }

        public Builder estado(Long estado) {
            if (estado != null) {
                this.sql += "and c.estado = '" + estado + "' ";
            }

            return this;
        }

        public Builder cidadeLike(String nome) {
            if (nome != "" && nome != null) {
                this.sql += "and UPPER(c.nome) like '" + nome.toUpperCase() + "%' ";
            }

            return this;
        }

        public Builder codigoLike(String cod) {
            if (cod != "" && cod != null) {
                this.sql += "and UPPER(c.cod) like '" + cod.toUpperCase() + "%' ";
            }

            return this;
        }

        public Builder cepLike(String cep) {
            if (cep != "" && cep != null) {
                this.sql += "and UPPER(c.cep) like '" + cep.toUpperCase() + "%' ";
            }

            return this;
        }

        public QueryStringCidade build() {
            return new QueryStringCidade(this);
        }
    }
}
