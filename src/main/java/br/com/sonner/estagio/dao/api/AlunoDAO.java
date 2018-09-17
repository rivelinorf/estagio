package br.com.sonner.estagio.dao.api;


import br.com.sonner.estagio.model.Parte2.segundo.Aluno;
import br.com.sonner.estagio.util.CustomException;

import java.util.List;

public interface AlunoDAO {
    void save(Aluno aluno);

    List<Aluno> getAll();

    void update(Aluno aluno);

    void delete(Long id) throws CustomException;

    Aluno getOne(Long id);

}
