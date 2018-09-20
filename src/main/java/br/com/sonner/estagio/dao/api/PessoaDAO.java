package br.com.sonner.estagio.dao.api;

import br.com.sonner.estagio.model.Pessoa;
import br.com.sonner.estagio.util.CustomException;

import java.util.List;

public interface PessoaDAO {
    void save(Pessoa diretor);

    List<Pessoa> getAll();

    void update(Pessoa diretor);

    void delete(Long id) throws CustomException;

    Pessoa getOne(Long id);
}
