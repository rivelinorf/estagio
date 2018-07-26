package br.com.sonner.estagio.dao.api;

import br.com.sonner.estagio.model.Estado;

import java.util.List;

public interface EstadoDAO {
    void save(Estado estado);
    List<Estado> getAll();
    Estado getOne(long id);
    void update(Estado estado);
    void delete(long id);
}
