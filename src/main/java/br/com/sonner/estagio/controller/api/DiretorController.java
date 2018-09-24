package br.com.sonner.estagio.controller.api;

import br.com.sonner.estagio.model.Diretor;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.DiretorFiltroVO;

import java.util.List;

public interface DiretorController {
    void save(Diretor diretor);

    List<Diretor> getAll();

    Diretor getOne(long id);

    void update(Diretor diretor);

    void delete(long id) throws CustomException;

}
