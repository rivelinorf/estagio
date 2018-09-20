package br.com.sonner.estagio.controller.api;

import br.com.sonner.estagio.model.Professor;
import br.com.sonner.estagio.util.CustomException;

import java.util.List;

public interface ProfessorController {
    void save(Professor professor);

    List<Professor> getAll();

    Professor getOne(long id);

    void update(Professor professor);

    void delete(long id) throws CustomException;
}
