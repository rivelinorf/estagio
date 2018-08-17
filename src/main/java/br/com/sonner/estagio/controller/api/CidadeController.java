package br.com.sonner.estagio.controller.api;

import java.util.List;

import br.com.sonner.estagio.model.Cidade;

public interface CidadeController {
    void save(Cidade cidade);

    List<Cidade> getAll();

    Cidade getOne(long id);

    void update(Cidade cidade);

    void delete(long id);

    List<String> validation(Cidade estado);
    
    List<Cidade> getByEstado(Long id);
}
