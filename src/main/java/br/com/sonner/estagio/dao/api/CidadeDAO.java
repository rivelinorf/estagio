package br.com.sonner.estagio.dao.api;

import br.com.sonner.estagio.model.Cidade;

import java.util.List;

public interface CidadeDAO {
    void save(Cidade estado);
    List<Cidade> getAll();
    Cidade getOne(long id);
    void update(Cidade estado);
    void delete(long id);
}
