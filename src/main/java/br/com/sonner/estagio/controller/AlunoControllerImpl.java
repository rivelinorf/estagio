package br.com.sonner.estagio.controller;

import br.com.sonner.estagio.controller.api.AlunoController;
import br.com.sonner.estagio.dao.AlunoDAOImpl;
import br.com.sonner.estagio.model.Aluno;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.AlunoFiltroVO;

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

    public List<Aluno> filtrar(AlunoFiltroVO alunosPesquisados) {
        return this.alunoDAO.pesquisaAluno(alunosPesquisados.getPessoa());

    }

    public List<Aluno> filtrarLike(AlunoFiltroVO alunosPesquisados) {
        return this.alunoDAO.pesquisaAluno(alunosPesquisados.getPessoa());
    }
}