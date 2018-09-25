package br.com.sonner.estagio.dao.api;

import br.com.sonner.estagio.model.Nota;
import br.com.sonner.estagio.model.TurmaDisciplina;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.NotaFiltroVO;
import br.com.sonner.estagio.vos.TurmaDisciplinaFiltroVO;

import java.util.List;

public interface TurmaDisciplinaDAO {
    void save(TurmaDisciplina turmaDisciplina);

    List<TurmaDisciplina> getAll();

    void update(TurmaDisciplina turmaDisciplina);

    void delete(Long id) throws CustomException;

    TurmaDisciplina getOne(Long id);

    List<TurmaDisciplina> pesquisaTurma(TurmaDisciplinaFiltroVO turmaDisciplinaFiltroVO);

}
