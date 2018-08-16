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
        private String sql = "select * from endereco where 1 ";
        
        public Builder numero(Integer numero) {
        	if(numero != null) {
        		this.sql += "and numero = "+ numero;
        	}
        	
        	return this;
        }

        public Builder cep(String cep) {
            if (cep != null && !cep.isEmpty()) {
                this.sql += "and cep = '"+ cep +"' ";
            }

            return this;
        }
        
        public Builder complemento(String complemento) {
            if (complemento != "") {
                this.sql += "and complemento like '"+ complemento +"%' ";
            }

            return this;
        }

        public Builder bairro(Long bairro) {
            if (bairro != null) {
                this.sql += "and endereco_bairro_fk like '"+ bairro +"%' ";
            }

            return this;
        }
		
        public Builder logradouro(Long logradouro) {
            if (logradouro != null) {
                this.sql += "and endereco_logradouro_fk like '"+ logradouro +"%' ";
            }

            return this;
        }
        
		public QueryStringEndereco build() {
			return new QueryStringEndereco(this);
		}
	}
}
