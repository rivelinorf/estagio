package br.com.sonner.estagio.dao.api;

import br.com.sonner.estagio.model.Professor;
import br.com.sonner.estagio.util.CustomException;

import java.util.List;

public interface ProfessorDAO {
    void save(Professor professor);

    List<Professor> getAll();

    void update(Professor professor);

    void delete(Long id) throws CustomException;

    Professor getOne(Long id);
}
