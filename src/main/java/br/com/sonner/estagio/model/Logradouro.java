package br.com.sonner.estagio.model;

public class Logradouro {

	private long id;
	private String nome;
	TipoLogradouro tipologradouro;
	Cidade cidade;

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Logradouro(String nome, TipoLogradouro tipologradouro,Cidade cidade) {
		this.nome = nome;
		this.tipologradouro = tipologradouro;
		this.cidade = cidade;
	}

	public TipoLogradouro getTipologradouro() {
		return tipologradouro;
	}

	public void setTipologradouro(TipoLogradouro tipologradouro) {
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
