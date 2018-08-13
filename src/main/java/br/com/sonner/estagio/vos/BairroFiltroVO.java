package br.com.sonner.estagio.vos;

import br.com.sonner.estagio.model.Cidade;

public class BairroFiltroVO {
	private String nome;
	private Long cidade;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getCidade() {
		return cidade;
	}
	public void setCidade(Long cidade) {
		this.cidade = cidade;
	}

}
