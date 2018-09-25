package br.com.sonner.estagio.controller;

import br.com.sonner.estagio.controller.api.ProfessorController;
import br.com.sonner.estagio.dao.ProfessorDAOImpl;
import br.com.sonner.estagio.model.Funcionario;
import br.com.sonner.estagio.model.Pessoa;
import br.com.sonner.estagio.model.Professor;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.ProfessorFiltroVO;

import java.util.ArrayList;
import java.util.List;

public class ProfessorControllerImpl implements ProfessorController {
    private ProfessorDAOImpl professorDAO;

    public ProfessorControllerImpl() {
        professorDAO = ProfessorDAOImpl.getInstance();
    }

    @Override
    public void save(Professor professor) {
        this.professorDAO.save(professor);

    }

    @Override
    public List<Professor> getAll() {
        return this.professorDAO.getAll();
    }

    @Override
    public Professor getOne(long id) {
        return this.professorDAO.getOne(id);
    }

    @Override
    public void update(Professor professor) {
        this.professorDAO.update(professor);

    }

    @Override
    public void delete(long id) throws CustomException {
        this.professorDAO.delete(id);

    }

    public List<Professor> filtrar(ProfessorFiltroVO professorsPesquisados) {
        return this.professorDAO.pesquisaProfessor(professorsPesquisados);
    }

    public List<Professor> filtrarLike(ProfessorFiltroVO professorsPesquisados) {
        return this.professorDAO.pesquisaProfessorLike(professorsPesquisados);
    }

    public List<String> validation(Professor professor) {
        List<String> erros = new ArrayList<>();

        if (professor.getFuncionario().getPessoa().getNome().length() == 0) {
            erros.add("Não é possível ter um professor sem nome");
        }

        if (professor.getFuncionario().getPessoa().getNome().length() > 50) {
            erros.add("Nome do professor não pode exceder 50 caracteres");
        }

        ProfessorFiltroVO professorFiltroVO = new ProfessorFiltroVO();

        professorFiltroVO.setFuncionario(new Funcionario());
        professorFiltroVO.getFuncionario().setPessoa(new Pessoa());
        professorFiltroVO.getFuncionario().getPessoa().setCpf(professor.getFuncionario().getPessoa().getCpf());

        if (filtrar(professorFiltroVO).size() > 0) {
            erros.add("CPF já cadastrador");
        }

        // regex para cpf

        return erros;
    }
}
