package br.com.sonner.estagio.dao.api;

import br.com.sonner.estagio.model.parte2.segundo.Disciplina;
import br.com.sonner.estagio.util.CustomException;

import java.util.List;

public interface DisciplinaDAO {
    void save(Disciplina disciplina);

    List<Disciplina> getAll();

    Disciplina getOne(long id);

    void update(Disciplina disciplina);

    void delete(long id) throws CustomException;

}
