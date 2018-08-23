package br.com.sonner.estagio.vos;

public class CidadeFiltroVO {
    private Long id;
    private String nome;
    private String cep;
    private String codigo;
    private Long estado;

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

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCod() {
        return codigo;
    }

    public void setCod(String codigo) {
        this.codigo = codigo;
    }

    public Long getEstado() {
        return estado;
    }

    public void setEstado(Long estado) {
        this.estado = estado;
    }
}
