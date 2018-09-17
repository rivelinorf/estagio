package br.com.sonner.estagio.model.Parte2.primeiro;

import br.com.sonner.estagio.model.Parte2.primeiro.Funcionario;

import javax.persistence.*;

@Entity
@Table
public class Diretor extends Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Diretor(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
