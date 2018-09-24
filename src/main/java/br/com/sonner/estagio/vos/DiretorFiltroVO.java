package br.com.sonner.estagio.vos;

import br.com.sonner.estagio.model.Funcionario;

public class DiretorFiltroVO {
    private Long id;
    private Funcionario funcionario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}
