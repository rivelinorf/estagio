package br.com.sonner.estagio.dao.api;

import br.com.sonner.estagio.model.parte2.segundo.Sala;
import br.com.sonner.estagio.util.CustomException;

import java.util.List;

public interface SalaDAO {
    void save(Sala sala);

    List<Sala> getAll();

    void update(Sala sala);

    void delete(Long id) throws CustomException;

    Sala getOne(Long id);


}
