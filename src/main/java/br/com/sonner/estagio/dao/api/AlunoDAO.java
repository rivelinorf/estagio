package br.com.sonner.estagio.dao.api;


import br.com.sonner.estagio.model.Aluno;
import br.com.sonner.estagio.model.Funcionario;
import br.com.sonner.estagio.model.Pessoa;
import br.com.sonner.estagio.util.CustomException;

import java.util.List;

public interface AlunoDAO {
    void save(Aluno aluno);

    List<Aluno> getAll();

    void update(Aluno aluno);

    void delete(Long id) throws CustomException;

    Aluno getOne(Long id);

    List<Aluno> pesquisaAluno(Pessoa pessoa);

    List<Aluno> pesquisaAlunoLike(Pessoa pessoa);

}
