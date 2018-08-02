package br.com.sonner.estagio.model;

public class Estado {
    private long id;
    private String nome;
    private String abv;

    public Estado() {
        this.nome= "teste";
    }

    public Estado(String nome, String abv) {
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

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
