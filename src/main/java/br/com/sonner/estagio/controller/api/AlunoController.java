package br.com.sonner.estagio.controller.api;

import br.com.sonner.estagio.model.parte2.segundo.Aluno;
import br.com.sonner.estagio.util.CustomException;

import java.util.List;

public interface AlunoController {
    void save(Aluno aluno);

    List<Aluno> getAll();

    Aluno getOne(long id);

    void update(Aluno aluno);

    void delete(long id) throws CustomException;
}
