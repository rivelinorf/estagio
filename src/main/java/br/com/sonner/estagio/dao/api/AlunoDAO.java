package br.com.sonner.estagio.dao.api;


import br.com.sonner.estagio.model.Aluno;
import br.com.sonner.estagio.model.Funcionario;
import br.com.sonner.estagio.model.Pessoa;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.AlunoFiltroVO;
import br.com.sonner.estagio.vos.TurmaDisciplinaFiltroVO;

import java.util.List;

public interface AlunoDAO {
    void save(Aluno aluno);

    List<Aluno> getAll();

    void update(Aluno aluno);

    void delete(Long id) throws CustomException;

    Aluno getOne(Long id);

    List<Aluno> pesquisaAluno(AlunoFiltroVO alunosPesquisados);

    List<Aluno> pesquisaAlunoLike(AlunoFiltroVO alunosPesquisados);

    List<Aluno> pesquisaAlunoPorTurmaDisciplina(TurmaDisciplinaFiltroVO turmaDisciplinaFiltroVO);

}
