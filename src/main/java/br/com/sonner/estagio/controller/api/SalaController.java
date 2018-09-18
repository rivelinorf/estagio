package br.com.sonner.estagio.controller.api;

import br.com.sonner.estagio.model.parte2.segundo.Sala;
import br.com.sonner.estagio.util.CustomException;

import java.util.List;

public interface SalaController {
    void save(Sala sala);

    List<Sala> getAll();

    Sala getOne(long id);

    void update(Sala sala);

    void delete(long id) throws CustomException;


}
