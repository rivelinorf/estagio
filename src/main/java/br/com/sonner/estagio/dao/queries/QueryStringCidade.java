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
            if (nome != ""){
                this.sql += "and nome LIKE '"+ nome +"%' ";
            }

            return this;
        }


        public Builder sigla(String abv) {
            if (abv != "") {
                this.sql += "and codigo LIKE '"+ abv +"%' ";
            }

            return this;
        }

        public Builder cep(String cep) {
            if (cep != "") {
                this.sql += "and cep LIKE '"+ cep +"%' ";
            }

            return this;
        }

        public Builder estado(Long estado) {
            if (estado != null) {
                this.sql += "and cidade_estado_fk = "+ estado;
            }

            return this;
        }

        public QueryStringCidade build() {
            return new QueryStringCidade(this);
        }
    }
}
