package br.com.sonner.estagio.model.Parte2;

import javax.persistence.*;

@Entity
@Table(name = "diretor")
public class Diretor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}