package br.com.sonner.estagio.model;

public class Bairro {
	private long id;
	private String nome;

	Cidade cidade;
	
	public Bairro(long id, String nome, Cidade cidade) {
		this.id =id;
		this.nome = nome;
		this.cidade = cidade;
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

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

}
