package br.com.sonner.estagio.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Estado {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String abv;

    @OneToMany (mappedBy = "estado")
    private List<Cidade> cidades =  new ArrayList<>();

    public Estado() {
        this.nome = "teste";
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
