package br.com.sonner.estagio.controller.api;

import br.com.sonner.estagio.model.TurmaDisciplina;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.TurmaDisciplinaFiltroVO;

import java.util.List;

public interface TurmaDisciplinaController {
    void save(TurmaDisciplina turmaDisciplina);

    List<TurmaDisciplina> getAll();

    TurmaDisciplina getOne(long id);

    void update(TurmaDisciplina turmaDisciplina);

    void delete(long id) throws CustomException;

    List<TurmaDisciplina> pesquisaTurma(TurmaDisciplinaFiltroVO turmaDisciplinaFiltroVO);
}
