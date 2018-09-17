package br.com.sonner.estagio.model.Parte2.primeiro;

import javax.persistence.*;

@Entity
@Table
public class Professor extends Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Professor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
