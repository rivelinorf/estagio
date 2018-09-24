package br.com.sonner.estagio.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Escola {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @ManyToOne
    private Endereco endereco;

    @OneToMany(mappedBy = "escola")
    private List<Sala> salas;

    @OneToMany(mappedBy = "escola")
    private List<Funcionario> funcionarios;


    @OneToMany(mappedBy = "escola")
    private List<Turma> turmas;

    @OneToMany(mappedBy = "escola")
    private List<Disciplina> disciplinas;

    public Escola(){

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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public void setSalas(List<Sala> salas) {
        this.salas = salas;
    }

    public List<Funcionario> getFuncionarios() { return funcionarios; }

    public void setFuncionarios(List<Funcionario> funcionarios) { this.funcionarios = funcionarios; }

    public List<Turma> getTurmas() { return turmas; }

    public void setTurmas(List<Turma> turmas) { this.turmas = turmas; }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
}
