package br.com.sonner.estagio.vos;

import java.math.BigDecimal;

public class NotaFiltroVO {
    private Long id;
    private BigDecimal nota;
    private Long aluno;
    private Long turmaDisciplina;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getNota() {
        return nota;
    }

    public void setNota(BigDecimal nota) {
        this.nota = nota;
    }

    public Long getAluno() {
        return aluno;
    }

    public void setAluno(Long aluno) {
        this.aluno = aluno;
    }

    public Long getTurmaDisciplina() {
        return turmaDisciplina;
    }

    public void setTurmaDisciplina(Long turmaDisciplina) {
        this.turmaDisciplina = turmaDisciplina;
    }
}
