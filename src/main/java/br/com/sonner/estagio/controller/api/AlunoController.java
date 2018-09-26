package br.com.sonner.estagio.controller.api;

import br.com.sonner.estagio.model.Aluno;
import br.com.sonner.estagio.model.TurmaDisciplina;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.TurmaDisciplinaFiltroVO;

import java.util.List;

public interface AlunoController {
    void save(Aluno aluno);

    List<Aluno> getAll();

    Aluno getOne(long id);

    void update(Aluno aluno);

    void delete(long id) throws CustomException;

    List<Aluno> pesquisaAlunoPorTurmaDisciplina(TurmaDisciplinaFiltroVO turmaDisciplinaFiltroVO);
}
