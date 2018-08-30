package br.com.sonner.estagio.controller.api;

import br.com.sonner.estagio.model.Logradouro;

import java.util.List;

public interface LogradouroController {

    Logradouro save(Logradouro logradouro);

    List<Logradouro> getAll();

    Logradouro getOne(long id);

    void update(Logradouro logradouro);

    void delete(long id);

    List<String> validation(Logradouro logradouro);
}

