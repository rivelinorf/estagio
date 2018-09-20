package br.com.sonner.estagio.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @ManyToOne
    private Escola escola;

    @OneToMany(mappedBy = "turma")
    private List<Matricula> matriculas;

    @OneToMany(mappedBy = "turma")
    private List<TurmaDisciplina> turmasDiscplina;

    public Turma() {
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

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

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    public List<TurmaDisciplina> getTurmasDiscplina() {
        return turmasDiscplina;
    }

    public void setTurmasDiscplina(List<TurmaDisciplina> turmasDiscplina) {
        this.turmasDiscplina = turmasDiscplina;
    }
}