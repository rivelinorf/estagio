package br.com.sonner.estagio.dao.api;

import br.com.sonner.estagio.model.Funcionario;
import br.com.sonner.estagio.model.Pessoa;
import br.com.sonner.estagio.util.CustomException;

import java.util.Date;
import java.util.List;

public interface FuncionarioDAO {
    void save(Funcionario diretor);

    List<Funcionario> getAll();

    void update(Funcionario diretor);

    void delete(Long id) throws CustomException;

    Funcionario getOne(Long id);

    List<Funcionario> pesquisaFuncionario(Pessoa pessoa, String admissao, Long escola);

    List<Funcionario> pesquisaFuncionarioLike(Pessoa pessoa, String admissao, Long escola);
}
