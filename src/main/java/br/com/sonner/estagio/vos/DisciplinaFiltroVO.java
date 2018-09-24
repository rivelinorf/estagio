package br.com.sonner.estagio.vos;

public class DisciplinaFiltroVO {
    private Long id;
    private String nome;
    private Long escola;
    private Long turmaDisciplina;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getEscola() {
        return escola;
    }

    public void setEscola(Long escola) {
        this.escola = escola;
    }

    public Long getTurmaDisciplina() {
        return turmaDisciplina;
    }

    public void setTurmaDisciplina(Long turmaDisciplina) {
        this.turmaDisciplina = turmaDisciplina;
    }
}
