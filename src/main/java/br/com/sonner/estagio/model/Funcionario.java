package br.com.sonner.estagio.model;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date admissao;

    @ManyToOne
    private Escola escola;

    @OneToOne(cascade = CascadeType.ALL)
    private Pessoa pessoa;

    @OneToMany(mappedBy = "funcionario")
    private List<Diretor> diretores = new ArrayList<>();

    @OneToMany(mappedBy = "funcionario")
    private List<Professor> professores = new ArrayList<>();

    public Funcionario() {

    }

    public String getAdmissao() {
        return new SimpleDateFormat("dd/MM/yyyy").format(this.admissao);
    }

    public void setAdmissao(Date admissao) {
        this.admissao = admissao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Diretor> getDiretores() {
        return diretores;
    }

    public void setDiretores(List<Diretor> diretores) {
        this.diretores = diretores;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }
}
