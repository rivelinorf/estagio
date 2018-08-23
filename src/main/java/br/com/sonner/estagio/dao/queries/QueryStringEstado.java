package br.com.sonner.estagio.dao.queries;

public class QueryStringEstado {
	private String sql = "SELECT * FROM estado WHERE 1 ";

	public String getSql() {
		return sql;
	}

	public QueryStringEstado(Builder builder) {
		this.sql = builder.sql;
	}

	public static class Builder {
		private String sql = "SELECT * FROM estado WHERE 1 ";

		public Builder estado(String nome) {
			if (nome != "" && nome != null) {
				this.sql += "and nome = '" + nome + "' ";
			}

			return this;
		}

		public Builder abv(String abv) {
			if (abv != "" && abv != null) {
				this.sql += "and abv = '" + abv + "' ";
			}

			return this;
		}

		public QueryStringEstado build() {
			return new QueryStringEstado(this);
		}
	}
}
