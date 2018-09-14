package br.com.sonner.estagio.controller.api;

import br.com.sonner.estagio.model.TipoLogradouro;
import br.com.sonner.estagio.util.CustomException;

import java.util.List;

public interface TipoLogradouroController {
    void save(TipoLogradouro tipoLogradouro);

    List<TipoLogradouro> getAll();

    TipoLogradouro getOne(long id);

    void update(TipoLogradouro tipoLogradouro);

    void delete(long id) throws CustomException;

    List<String> validation(TipoLogradouro tipoLogradouro);


}
