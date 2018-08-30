package br.com.sonner.estagio.controller.api;

import br.com.sonner.estagio.model.TipoLogradouro;

import java.util.List;

public interface TipoLogradouroController {
    void save(TipoLogradouro tipoLogradouro);

    List<TipoLogradouro> getAll();

    TipoLogradouro getOne(long id);

    void update(TipoLogradouro tipoLogradouro);

    void delete(long id);

    List<String> validation(TipoLogradouro tipoLogradouro);


}
