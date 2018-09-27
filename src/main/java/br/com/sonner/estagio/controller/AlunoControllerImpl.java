package br.com.sonner.estagio.controller;

import br.com.sonner.estagio.controller.api.AlunoController;
import br.com.sonner.estagio.dao.AlunoDAOImpl;
import br.com.sonner.estagio.model.Aluno;
import br.com.sonner.estagio.model.Pessoa;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.AlunoFiltroVO;
import br.com.sonner.estagio.vos.TurmaDisciplinaFiltroVO;

import java.util.ArrayList;
import java.util.List;

public class AlunoControllerImpl implements AlunoController {
    private AlunoDAOImpl alunoDAO;

    public AlunoControllerImpl() {
        alunoDAO = AlunoDAOImpl.getInstance();
    }

    @Override
    public void save(Aluno aluno) {
        this.alunoDAO.save(aluno);

    }

    @Override
    public List<Aluno> getAll() {
        return this.alunoDAO.getAll();
    }

    @Override
    public Aluno getOne(long id) {
        return this.alunoDAO.getOne(id);
    }

    @Override
    public void update(Aluno aluno) {
        this.alunoDAO.update(aluno);
    }

    @Override
    public void delete(long id) throws CustomException {
        this.alunoDAO.delete(id);
    }

    @Override
    public List<Aluno> pesquisaAlunoPorTurmaDisciplina(TurmaDisciplinaFiltroVO turmaDisciplinaFiltroVO) {
        return this.alunoDAO.pesquisaAlunoPorTurmaDisciplina(turmaDisciplinaFiltroVO);
    }

    public List<Aluno> filtrar(AlunoFiltroVO alunosPesquisados) {
        return this.alunoDAO.pesquisaAluno(alunosPesquisados);

    }

    public List<Aluno> filtrarLike(AlunoFiltroVO alunosPesquisados) {
        return this.alunoDAO.pesquisaAlunoLike(alunosPesquisados);
    }

    public List<String> validation(Aluno aluno) {
        List<String> erros = new ArrayList<>();

        if (aluno.getPessoa().getNome().length() == 0) {
            erros.add("Não é possível ter um aluno sem nome");
        }

        if (aluno.getPessoa().getNome().length() > 50) {
            erros.add("Nome do aluno não pode exceder 50 caracteres");
        }

        AlunoFiltroVO alunoFiltroVO = new AlunoFiltroVO();

        alunoFiltroVO.setPessoa(new Pessoa());
        alunoFiltroVO.getPessoa().setNome(aluno.getPessoa().getCpf());

        return erros;
    }
}