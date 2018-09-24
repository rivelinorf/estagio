package br.com.sonner.estagio.dao.api;

import br.com.sonner.estagio.model.Disciplina;
import br.com.sonner.estagio.model.Turma;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.DisciplinaFiltroVO;
import br.com.sonner.estagio.vos.TurmaFiltroVO;

import java.util.List;

public interface DisciplinaDAO {
    void save(Disciplina disciplina);

    List<Disciplina> getAll();

    void update(Disciplina disciplina);

    void delete(Long id) throws CustomException;

    Disciplina getOne(Long id);

    List<Disciplina> pesquisaDisciplinaLike(DisciplinaFiltroVO vo);

    List<Disciplina> pesquisaDisciplina(DisciplinaFiltroVO vo);
}