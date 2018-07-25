package br.com.sonner.estagio.dao.api;

import br.com.sonner.estagio.model.Estado;

import java.util.List;

public interface EstadoDAO {
    void save(Estado estado);
    List<Estado> get(Estado estado);
    void update(Estado estado);
    void delete(Estado estado);
}
