package br.com.sonner.estagio.vos;

import br.com.sonner.estagio.model.Pessoa;

public class AlunoFiltroVO {
    private Long id;
    private Pessoa pessoa;


    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
