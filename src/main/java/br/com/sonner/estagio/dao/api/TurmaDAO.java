package br.com.sonner.estagio.dao.api;

import br.com.sonner.estagio.model.parte2.segundo.Turma;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.TurmaFiltroVO;

import java.util.List;

public interface TurmaDAO {
    void save(Turma turma);

    List<Turma> getAll();

    void update(Turma turma);

    void delete(Long id) throws CustomException;

    Turma getOne(Long id);

    List<Turma> pesquisaTurmaLike(TurmaFiltroVO vo);

    List<Turma> pesquisaTurma(TurmaFiltroVO vo);
}
