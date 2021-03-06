package br.com.sonner.estagio.controller.api;

import java.util.List;

import br.com.sonner.estagio.model.Bairro;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.BairroFiltroVO;

public interface BairroController {
    void save(Bairro bairro);

    List<Bairro> getAll();

    Bairro getOne(long id);

    void update(Bairro bairro);

    void delete(long id) throws CustomException;

    List<Bairro> filtrar(BairroFiltroVO bairrosPesquisados);

    List<String> validation(Bairro bairro);

    List<Bairro> filtrarLike(BairroFiltroVO bairrosPesquisados);

}
