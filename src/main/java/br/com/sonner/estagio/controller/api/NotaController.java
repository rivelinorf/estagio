package br.com.sonner.estagio.controller.api;

import br.com.sonner.estagio.model.Nota;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.NotaFiltroVO;

import java.util.List;

public interface NotaController {
    void save(Nota nota);

    List<Nota> getAll();

    Nota getOne(long id);

    void update(Nota nota);

    void delete(long id) throws CustomException;

    List<String> validation(Nota nota);

    List<Nota> pesquisaNota(NotaFiltroVO notaFiltroVO);
}
