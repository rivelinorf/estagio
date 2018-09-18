package br.com.sonner.estagio.controller.api;

import br.com.sonner.estagio.model.TipoLogradouro;
import br.com.sonner.estagio.model.parte2.segundo.Disciplina;
import br.com.sonner.estagio.util.CustomException;

import java.util.List;

public interface DisciplinaController {
    void save(Disciplina disciplina);

    List<Disciplina> getAll();

    Disciplina getOne(long id);

    void update(Disciplina disciplina);

    void delete(long id) throws CustomException;

    List<String> validation(Disciplina disciplina);

}
