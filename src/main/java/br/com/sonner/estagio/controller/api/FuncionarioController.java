package br.com.sonner.estagio.controller.api;

import br.com.sonner.estagio.model.parte2.primeiro.Funcionario;
import br.com.sonner.estagio.util.CustomException;

import java.util.List;

public interface FuncionarioController {
    void save(Funcionario funcionario);

    List<Funcionario> getAll();

    Funcionario getOne(long id);

    void update(Funcionario funcionario);

    void delete(long id) throws CustomException;
}
