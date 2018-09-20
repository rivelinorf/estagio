package br.com.sonner.estagio.controller;


import br.com.sonner.estagio.controller.api.DisciplinaController;
import br.com.sonner.estagio.dao.DisciplinaDAOImpl;
import br.com.sonner.estagio.model.Disciplina;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.DisciplinaFiltroVO;

import java.util.ArrayList;
import java.util.List;

public class DisciplinaControllerImpl implements DisciplinaController {
    private DisciplinaDAOImpl disciplinaDAO;

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

        if (disciplina.getNome().length() == 0) {
            erros.add("Nao é possível inserir uma disciplina  sem nome ");
        }

        if (disciplina.getNome().length() > 20) {
            erros.add("A disciplina  não pode exceder 20 caracteres ");
        }

        return erros;
    }

    public List<Disciplina> filtrar(DisciplinaFiltroVO vo) {
        return this.disciplinaDAO.pesquisaDisciplina((vo.getNome()));


    }

    public Object filtrarLike(DisciplinaFiltroVO vo) {
        return this.disciplinaDAO.pesquisaTipoLogradouroLike(vo);
    }
}
