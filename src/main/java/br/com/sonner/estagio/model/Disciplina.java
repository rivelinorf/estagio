package br.com.sonner.estagio.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;

    @ManyToOne
    private TurmaDisciplina turmasDiscplina;

    @ManyToOne
    private Escola escola;


    public Disciplina() {

    }

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

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }

    public TurmaDisciplina getTurmasDiscplina() {
        return turmasDiscplina;
    }

    public void setTurmasDiscplina(TurmaDisciplina turmasDiscplina) {
        this.turmasDiscplina = turmasDiscplina;
    }
}
