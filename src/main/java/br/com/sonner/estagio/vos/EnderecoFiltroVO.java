package br.com.sonner.estagio.vos;

public class EnderecoFiltroVO {
	private Long id;
	private Integer numero;
	private String cep;
	private String complemento;
	private Long logradouro;
	private Long bairro;

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Long getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Long logradouro) {
		this.logradouro = logradouro;
	}

	public Long getBairro() {
		return bairro;
	}

	public void setBairro(Long bairro) {
		this.bairro = bairro;
	}

}
