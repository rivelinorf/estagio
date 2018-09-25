package br.com.sonner.estagio.model;

import javax.persistence.*;

@Entity
@Table
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @ManyToOne
    private Escola escola;

    public Sala() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Escola getEscola() { return escola; }

    public void setEscola(Escola escola) { this.escola = escola; }
}
