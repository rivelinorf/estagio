package br.com.sonner.estagio.vos;

import br.com.sonner.estagio.model.Pessoa;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FuncionarioFiltroVO {
    private Long id;
    private Date admissao;
    private Long escola;
    private Pessoa pessoa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdmissao() {
        if (this.admissao != null) {
            return new SimpleDateFormat("dd/MM/yyyy").format(this.admissao);
        } else {
            return null;
        }
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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
