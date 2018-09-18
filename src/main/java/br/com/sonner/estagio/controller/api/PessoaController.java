package br.com.sonner.estagio.controller.api;

import br.com.sonner.estagio.model.parte2.primeiro.Pessoa;
import br.com.sonner.estagio.util.CustomException;

import java.util.List;

public interface PessoaController {
    void save(Pessoa pessoa);

    List<Pessoa> getAll();

    Pessoa getOne(long id);

    void update(Pessoa pessoa);

    void delete(long id) throws CustomException;

}
