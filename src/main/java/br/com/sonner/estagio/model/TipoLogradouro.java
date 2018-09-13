package br.com.sonner.estagio.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class TipoLogradouro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;


    @OneToMany(mappedBy = "tipologradouro")
    private List<Logradouro> logradouros;


    public TipoLogradouro() {
    }

    public TipoLogradouro(String nome) {
        this.nome = nome;
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

    public List<Logradouro> getLogradouros() {
        return logradouros;
    }

    public void setLogradouros(List<Logradouro> logradouros) {
        this.logradouros = logradouros;
    }
}
