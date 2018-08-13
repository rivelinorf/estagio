package br.com.sonner.estagio.dao.queries;

public class QueryStringBairro {
	private String sql = "SELECT * FROM cidade WHERE 1 ";

	public String getSql() {
		return sql;
	}

	public QueryStringBairro(Builder builder) {
		this.sql = builder.sql;
	}

	public static class Builder {
		 private String sql = "SELECT * FROM bairro WHERE 1 ";;

        public Builder bairro(String nome) {
            if (nome != ""){
                this.sql += "and nome LIKE '"+ nome +"%' ";
            }

            return this;
        }
		
        public Builder cidade(Long cidade) {
            if (cidade != null) {
                this.sql += "and bairro_cidade_fk LIKE '"+ cidade +"%' ";
            }

            return this;
        }
		
		public QueryStringBairro build() {
			return new QueryStringBairro(this);
		}
	}

}