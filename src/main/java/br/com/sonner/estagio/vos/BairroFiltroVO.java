package br.com.sonner.estagio.vos;

import br.com.sonner.estagio.model.Cidade;

public class BairroFiltroVO {
	private String nome;
	private Cidade cidade;
	private Long id;
	
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
