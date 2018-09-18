package br.com.sonner.estagio.vos;

import java.util.Date;

public class FuncionarioFiltroVO {
    private Long id;
    private Date admissao;
    private Long escola;
    private Long pessoa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAdmissao() {
        return admissao;
    }

    public void setAdmissao(Date admissao) {
        this.admissao = admissao;
    }

    public Long getEscola() {
        return escola;
    }

    public void setEscola(Long escola) {
        this.escola = escola;
    }

    public Long getPessoa() {
        return pessoa;
    }

    public void setPessoa(Long pessoa) {
        this.pessoa = pessoa;
    }
}
