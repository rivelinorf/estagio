package br.com.sonner.estagio.model.parte2.segundo;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class TurmaDisciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Disciplina disciplina;

    @ManyToOne
    private Turma turma;

    @OneToMany (mappedBy = "turmaDisciplina")
    private List<Nota> notas;

    public TurmaDisciplina() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }
}
