package br.com.sonner.estagio.dao.api;

import br.com.sonner.estagio.model.Nota;
import br.com.sonner.estagio.util.CustomException;

import java.util.List;

public interface NotaDAO {
    void save(Nota nota);

    List<Nota> getAll();

    void update(Nota nota);

    void delete(Long id) throws CustomException;

    Nota getOne(Long id);
}
