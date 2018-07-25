package br.com.sonner.estagio.model;

public class Logradouro {

	private long id;
	private String nome;
	TipoLogradouro tipologradouro;

	public Logradouro(long id, String nome, TipoLogradouro tipologradouro) {
		this.id = id;
		this.nome = nome;
		this.tipologradouro = tipologradouro;
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

}
