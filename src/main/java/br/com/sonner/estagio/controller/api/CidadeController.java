package br.com.sonner.estagio.controller.api;

import br.com.sonner.estagio.model.Cidade;

import java.util.List;

public interface CidadeController {
    void save(Cidade cidade);
    List<Cidade> getAll();
    Cidade getOne(long id);
    void update(Cidade cidade);
    void delete(long id);
}
