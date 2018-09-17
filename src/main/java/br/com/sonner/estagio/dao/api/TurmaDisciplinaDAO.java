package br.com.sonner.estagio.dao.api;

import br.com.sonner.estagio.model.parte2.segundo.TurmaDisciplina;
import br.com.sonner.estagio.util.CustomException;

import java.util.List;

public interface TurmaDisciplinaDAO {
    void save(TurmaDisciplina turmaDisciplina);

    List<TurmaDisciplina> getAll();

    void update(TurmaDisciplina turmaDisciplina);

    void delete(Long id) throws CustomException;

    TurmaDisciplina getOne(Long id);
}
