package br.com.sonner.estagio.controller;

import br.com.sonner.estagio.controller.api.AlunoController;
import br.com.sonner.estagio.dao.AlunoDAOImpl;
import br.com.sonner.estagio.model.Aluno;
import br.com.sonner.estagio.model.Diretor;
import br.com.sonner.estagio.model.Funcionario;
import br.com.sonner.estagio.model.Pessoa;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.AlunoFiltroVO;
import br.com.sonner.estagio.vos.DiretorFiltroVO;

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

    public List<Aluno> filtrar(AlunoFiltroVO vo) {
        return this.alunoDAO.pesquisaAluno(vo);

    }

    public List<Aluno> filtrarLike(AlunoFiltroVO vo) {
        return this.alunoDAO.pesquisaAlunoLike(vo);
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