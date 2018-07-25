package br.com.sonner.estagio.model;

public class Estado {
    private Long id;
    private String nome;
    private String abv;

    public Estado(Long id, String nome, String abv) {
        this.id = id;
        this.nome = nome;
        this.abv = abv;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAbv() {
        return abv;
    }

    public void setAbv(String abv) {
        this.abv = abv;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
