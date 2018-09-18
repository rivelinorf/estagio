package br.com.sonner.estagio.controller.api;

import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.model.parte2.segundo.Escola;
import br.com.sonner.estagio.util.CustomException;

import java.util.List;

public interface EscolaController {
    void save(Escola escola);

    List<Escola> getAll();

    Escola getOne(long id);

    void update(Escola escola);

    void delete(long id) throws CustomException;

    List<String> validation(Escola escola);
}