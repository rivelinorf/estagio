package br.com.sonner.estagio.dao.queries;

public class QueryStringEndereco {
	private String sql = "select * from endereco where 1 ";

	public String getSql() {
		return sql;
	}

	public QueryStringEndereco(Builder builder) {
		this.sql = builder.sql;
	}

	public static class Builder {
        private String sql = "SELECT * FROM endereco WHERE 1 ";

        public Builder cep(String cep) {
            if (cep != "") {
                this.sql += "and cep LIKE '"+ cep +"%' ";
            }

            return this;
        }
		
		public QueryStringEndereco build() {
			return new QueryStringEndereco(this);
		}
	}
}