package br.com.sonner.estagio.controller.api;

import br.com.sonner.estagio.model.Logradouro;
import br.com.sonner.estagio.util.CustomException;

import java.util.List;

public interface LogradouroController {

    Logradouro save(Logradouro logradouro);

    List<Logradouro> getAll();

    Logradouro getOne(long id);

    void update(Logradouro logradouro);

    void delete(long id) throws CustomException;

    List<String> validation(Logradouro logradouro);
}

