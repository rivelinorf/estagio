package br.com.sonner.estagio.dao.api;

import br.com.sonner.estagio.model.Disciplina;
import br.com.sonner.estagio.util.CustomException;

import java.util.List;

public interface DisciplinaDAO {
    void save(Disciplina disciplina);

    List<Disciplina> getAll();

    Disciplina getOne(Long id);

    void update(Disciplina disciplina);

    void delete(Long id) throws CustomException;

}
