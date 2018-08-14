package br.com.sonner.estagio.vos;

public class LogradouroFiltroVO {
    private Long id;
    private String nome;
    private Long cidade;
    private Long tipologradouro;


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

    public Long getCidade() {
        return cidade;
    }

    public void setCidade(Long cidade) {
        this.cidade = cidade;
    }

    public Long getTipologradouro() {
        return tipologradouro;
    }

    public void setTipologradouro(Long tipologradouro) {
        this.tipologradouro = tipologradouro;
    }

}
