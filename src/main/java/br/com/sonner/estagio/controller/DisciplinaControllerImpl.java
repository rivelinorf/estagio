package br.com.sonner.estagio.controller;

import br.com.sonner.estagio.controller.api.DisciplinaController;
import br.com.sonner.estagio.dao.DisciplinaDAOImpl;
import br.com.sonner.estagio.dao.api.DisciplinaDAO;
import br.com.sonner.estagio.model.Disciplina;
import br.com.sonner.estagio.model.Turma;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.DisciplinaFiltroVO;
import br.com.sonner.estagio.vos.TurmaFiltroVO;

import java.util.ArrayList;
import java.util.List;

public class DisciplinaControllerImpl implements DisciplinaController {
    private DisciplinaDAO disciplinaDAO;

    public DisciplinaControllerImpl() {
        disciplinaDAO = DisciplinaDAOImpl.getInstance();
    }


    @Override
    public void save(Disciplina disciplina) {
        this.disciplinaDAO.save(disciplina);

    }

    @Override
    public List<Disciplina> getAll() {
        return this.disciplinaDAO.getAll();
    }

    @Override
    public Disciplina getOne(long id) {
        return this.disciplinaDAO.getOne(id);
    }

    @Override
    public void update(Disciplina disciplina) {
        this.disciplinaDAO.update(disciplina);

    }

    @Override
    public void delete(long id) throws CustomException {
        this.disciplinaDAO.delete(id);

    }

    @Override
    public List<String> validation(Disciplina disciplina) {
        List<String> erros = new ArrayList<>();

        if (disciplina.getNome() == null || disciplina.getNome().isEmpty()) {
            erros.add("O nome da disciplina não pode ser vazio");
        }


        if (disciplina.getNome().length() > 50) {
            erros.add("O nome da disciplina não pode exceder 50 caracteres  ");
        }


        if (disciplina.getEscola() == null) {
            erros.add("Impossível ter uma disciplina sem uma escola selecionada");
        }
        return erros;
    }

    public List<Disciplina> filtrar(DisciplinaFiltroVO vo) {
        return this.disciplinaDAO.pesquisaDisciplina(vo);
    }

    public List<Disciplina> filtrarLike(DisciplinaFiltroVO vo) {
        return this.disciplinaDAO.pesquisaDisciplinaLike(vo);

    }
}