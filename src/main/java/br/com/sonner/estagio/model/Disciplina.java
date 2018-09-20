package br.com.sonner.estagio.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;

    @OneToMany(mappedBy = "disciplina")
    private List<TurmaDisciplina> turmasDiscplina;


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

    public List<TurmaDisciplina> getTurmasDiscplina() {
        return turmasDiscplina;
    }

    public void setTurmasDiscplina(List<TurmaDisciplina> turmasDiscplina) {
        this.turmasDiscplina = turmasDiscplina;
    }
}
