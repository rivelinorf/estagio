package br.com.sonner.estagio.dao.api;

import br.com.sonner.estagio.model.parte2.segundo.Turma;
import br.com.sonner.estagio.util.CustomException;

import java.util.List;

public interface TurmaDAO {
    void save(Turma turma);

    List<Turma> getAll();

    void update(Turma turma);

    void delete(Long id) throws CustomException;

    Turma getOne(Long id);
}
