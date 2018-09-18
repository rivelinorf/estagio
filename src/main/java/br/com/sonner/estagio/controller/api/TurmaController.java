package br.com.sonner.estagio.controller.api;

import br.com.sonner.estagio.model.Bairro;
import br.com.sonner.estagio.model.parte2.segundo.Turma;
import br.com.sonner.estagio.util.CustomException;

import java.util.List;

public interface TurmaController {
    void save(Turma turma);

    List<Turma> getAll();

    Turma getOne(long id);

    void update(Turma turma);

    void delete(long id) throws CustomException;


}
