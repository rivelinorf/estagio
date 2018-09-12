package br.com.sonner.estagio.model;

import javax.persistence.*;

@Entity
@Table(name="endereco")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numero;
    private String cep;
    private String complemento;

    @ManyToOne
    @JoinColumn(name = "endereco_bairro_fk", nullable = false)
    private Bairro bairro;

    @ManyToOne
    @JoinColumn(name = "endereco_logradouro_fk", nullable = false)
    private Logradouro logradouro;

    public Endereco() {

    }

    public Endereco(Integer numero, String cep, String complemento, Bairro bairro, Logradouro logradouro) {
        this.numero = numero;
        this.cep = cep;
        this.complemento = complemento;
        this.bairro = bairro;
        this.logradouro = logradouro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public Logradouro getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(Logradouro logradouro) {
        this.logradouro = logradouro;
    }

}
