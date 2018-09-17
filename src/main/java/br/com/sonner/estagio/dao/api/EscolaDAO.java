package br.com.sonner.estagio.dao.api;

import br.com.sonner.estagio.model.parte2.segundo.Escola;
import br.com.sonner.estagio.util.CustomException;

import java.util.List;

public interface EscolaDAO {
    void save(Escola escola);

    List<Escola> getAll();

    void update(Escola escola);

    void delete(Long id) throws CustomException;

    Escola getOne(Long id);

}
