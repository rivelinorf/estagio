package br.com.sonner.estagio.model.Parte2.segundo;

import br.com.sonner.estagio.model.Parte2.primeiro.Pessoa;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Aluno extends Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Matricula matricula;

    @OneToMany(mappedBy = "aluno")
    private List<Nota> notas;

    public Aluno() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }
}
