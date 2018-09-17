package br.com.sonner.estagio.dao.api;

import br.com.sonner.estagio.model.parte2.primeiro.Diretor;
import br.com.sonner.estagio.util.CustomException;

import java.util.List;

public interface DiretorDAO {
    void save(Diretor diretor);

    List<Diretor> getAll();

    void update(Diretor diretor);

    void delete(Long id) throws CustomException;

    Diretor getOne(Long id);
}
