package br.com.sonner.estagio.model;

public class TipoLogradouro {
	private long id;
	private String nome;

	public TipoLogradouro( String nome) {
		this.nome = nome;
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
