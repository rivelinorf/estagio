package br.com.sonner.estagio.dao.queries;

public class QueryStringEndereco {
    private String sql = "SELECT e FROM endereco WHERE 1=1 ";

    public String getSql() {
        return sql;
    }

    public QueryStringEndereco(Builder builder) {
        this.sql = builder.sql;
    }

    public static class Builder {
        private String sql = "SELECT e FROM endereco WHERE 1=1 ";

        public Builder numero(Integer numero) {
            if (numero != null) {
                this.sql += "and e.numero = '" + numero + "' ";
            }

            return this;
        }

        public Builder complemento(String complemento) {
            if (complemento != "" && complemento != null) {
                this.sql += "and e.complemento = '" + complemento + "' ";
            }

            return this;
        }

        public Builder cep(String cep) {
            if (cep != "" && cep != null) {
                this.sql += "and e.cep = '" + cep + "' ";
            }

            return this;
        }

        public Builder bairro(Long bairro) {
            if (bairro != null) {
                this.sql += "and e.bairro.id = '" + bairro + "' ";
            }

            return this;
        }

        public Builder logradouro(Long logradouro) {
            if (logradouro != null) {
                this.sql += "and e.logradouro.id = '" + logradouro + "' ";
            }

            return this;
        }

        public Builder numeroLike(Integer numero) {
            if (numero != null) {
                this.sql += "and e.numero like '" + numero + "%' ";
            }

            return this;
        }

        public Builder complementoLike(String complemento) {
            if (complemento != "" && complemento != null) {
                this.sql += "and UPPER(e.complemento) like '" + complemento.toUpperCase() + "%' ";
            }

            return this;
        }

        public Builder cepLike(String cep) {
            if (cep != "" && cep != null) {
                this.sql += "and UPPER(e.cep) like '" + cep.toUpperCase() + "%' ";
            }

            return this;
        }

        public QueryStringEndereco build() {
            return new QueryStringEndereco(this);
        }
    }
}