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

    @OneToOne(mappedBy = "funcionario")
    private Diretor diretores;

    @OneToOne(mappedBy = "funcionario")
    private Professor professores;

    public Funcionario() {

    }

    public String getAdmissao() {
        if (this.admissao != null) {
            return new SimpleDateFormat("dd/MM/yyyy").format(this.admissao);
        } else {
            return null;
        }
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

    public Professor getProfessores() {
        return professores;
    }

    public void setProfessores(Professor professores) {
        this.professores = professores;
    }

    public Diretor getDiretores() {
        return diretores;
    }

    public void setDiretores(Diretor diretores) {
        this.diretores = diretores;
    }
}
