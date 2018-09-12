package br.com.sonner.estagio.model;

import javax.persistence.*;
import java.util.List;

@Entity
//@Table(name = "logradouro")
public class Logradouro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    //@JoinColumn(name = "logradouro_tipologradouro_fk", nullable = false)
    private TipoLogradouro tipologradouro;


    @OneToMany(mappedBy = "logradouro", cascade = CascadeType.PERSIST)
    private List<Endereco> enderecos;

    @ManyToOne
    //@JoinColumn(name = "logradouro_cidade_fk", nullable = false)
    private Cidade cidade;

    private String nome;

    public Logradouro() {

    }

    public Logradouro(String nome, TipoLogradouro tipologradouro, Cidade cidade) {
        this.nome = nome;
        this.tipologradouro = tipologradouro;
        this.cidade = cidade;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public TipoLogradouro getTipologradouro() {
        return tipologradouro;
    }

    public void setTipologradouro(TipoLogradouro tipologradouro) {
        this.tipologradouro = tipologradouro;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
}
