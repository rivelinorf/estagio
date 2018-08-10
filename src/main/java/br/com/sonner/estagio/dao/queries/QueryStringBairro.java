package br.com.sonner.estagio.dao.queries;

import br.com.sonner.estagio.model.Cidade;

public class QueryStringBairro {
	private String sql = "select * from bairro where 1 ";
	
	public String getSql() {
		return sql;
	}
	
	public QueryStringBairro(Builder builder) {
		this.sql = builder.sql;
	}
	
	public static class Builder{
		private String sql = "select * from bairro where 1 ";
		
		public Builder nome(String nome) {
			if(nome != "") {
				this.sql += "and nome like '"+ nome +"%' ";
			}
			return this;
		}
		
		public Builder cidade(Cidade cidade) {
			if(cidade.getNome() != "") {
				this.sql += "and bairro_cidade_fk like "+ cidade.getId();
			}
			return this;
		}
		
		public QueryStringBairro build() {
			return new QueryStringBairro(this);
		}
	}
}
