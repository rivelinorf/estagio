package br.com.sonner.estagio.vos;

public class TurmaFiltroVO {
    private Long id;
    private String nome;
    private Long escola;

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
}
