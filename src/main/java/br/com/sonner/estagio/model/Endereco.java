package br.com.sonner.estagio.model;

public class Endereco {
	private long id;
	private Integer numero;
	private String cep;

	private Bairro bairro;
	private Logradouro logradouro;

	public Endereco(Integer numero, String cep, Bairro bairro, Logradouro logradouro) {

		this.numero = numero;
		this.cep = cep;
		this.bairro = bairro;
		this.logradouro = logradouro;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}

}
