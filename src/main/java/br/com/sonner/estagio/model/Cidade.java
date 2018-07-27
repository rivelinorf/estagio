package br.com.sonner.estagio.model;

public class Cidade {
    private long id;
    private String nome;
    private String cod;
    private String cep;

    private Estado estado;

    public Cidade() {
    }

    public Cidade(String nome, String cod, String cep, Estado estado) {
        this.nome = nome;
        this.cod = cod;
        this.cep = cep;
        this.estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
