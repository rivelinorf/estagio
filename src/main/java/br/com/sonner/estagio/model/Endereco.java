package br.com.sonner.estagio.model;

public class Endereco {
	private long id;
	private String nome;
	private String cep;

	Bairro bairro;
	Logradouro logradouro;
	
	public Endereco(long id, String nome, String cep, Bairro bairro, Logradouro logradouro) {
		this.id = id;
		this.nome = nome;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
