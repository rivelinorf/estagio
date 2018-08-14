package br.com.sonner.estagio.controller.api;

import br.com.sonner.estagio.model.Estado;
import br.com.sonner.estagio.vos.EstadoFiltroVO;

import java.util.List;

public interface EstadoController {
    void save(Estado estado);

    List<Estado> getAll();

    Estado getOne(long id);

    void update(Estado estado);

    void delete(long id);

    List<String> validation(Estado estado);
}
