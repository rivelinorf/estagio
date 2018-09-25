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

    @ManyToOne
    private TurmaDisciplina turmasDisciplina;

    public Turma() {
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

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    public TurmaDisciplina getTurmasDisciplina() {
        return turmasDisciplina;
    }

    public void setTurmasDisciplina(TurmaDisciplina turmasDisciplina) {
        this.turmasDisciplina = turmasDisciplina;
    }
}

