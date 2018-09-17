package br.com.sonner.estagio.dao.api;

import br.com.sonner.estagio.model.parte2.segundo.Matricula;
import br.com.sonner.estagio.util.CustomException;

import java.util.List;

public interface MatriculaDAO {
    void save(Matricula matricula);

    List<Matricula> getAll();

    void update(Matricula matricula);

    void delete(Long id) throws CustomException;

    Matricula getOne(Long id);

}
