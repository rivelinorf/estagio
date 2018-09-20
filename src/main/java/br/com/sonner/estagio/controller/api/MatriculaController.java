package br.com.sonner.estagio.controller.api;

import br.com.sonner.estagio.model.Matricula;
import br.com.sonner.estagio.util.CustomException;

import java.util.List;

public interface MatriculaController {
    void save(Matricula matricula);

    List<Matricula> getAll();

    Matricula getOne(long id);

    void update(Matricula matricula);

    void delete(long id) throws CustomException;

    List<String> validation(Matricula matricula);


}
