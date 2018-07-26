package br.com.sonner.estagio.dao.api;

import br.com.sonner.estagio.model.Cidade;

import java.util.List;

public interface CidadeDAO {
    void save(Cidade cidade);
    List<Cidade> get();
    void update(Cidade cidade);
    void delete(Long id);
}
