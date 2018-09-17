package br.com.sonner.estagio.dao.api;

import br.com.sonner.estagio.model.parte2.primeiro.Funcionario;
import br.com.sonner.estagio.util.CustomException;

import java.util.List;

public interface FuncionarioDAO {
    void save(Funcionario diretor);

    List<Funcionario> getAll();

    void update(Funcionario diretor);

    void delete(Long id) throws CustomException;

    Funcionario getOne(Long id);
}
