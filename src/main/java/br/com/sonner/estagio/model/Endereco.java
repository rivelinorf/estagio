package br.com.sonner.estagio.model;

import br.com.sonner.estagio.model.parte2.primeiro.Pessoa;
import br.com.sonner.estagio.model.parte2.segundo.Escola;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numero;
    private String cep;
    private String complemento;

    @ManyToOne
    private Bairro bairro;

    @ManyToOne
    private Logradouro logradouro;

    @OneToMany
    private List<Pessoa> pessoas = new ArrayList<>();

    @OneToOne
    private Escola escola;

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

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }
}
