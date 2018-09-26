package br.com.sonner.estagio.controller;

import br.com.sonner.estagio.controller.api.TurmaDisciplinaController;
import br.com.sonner.estagio.dao.TurmaDisciplinaDAOImpl;
import br.com.sonner.estagio.model.TurmaDisciplina;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.TurmaDisciplinaFiltroVO;

import java.util.List;

public class TurmaDisciplinaControllerImpl implements TurmaDisciplinaController {
    private TurmaDisciplinaDAOImpl turmaDisciplinaDAO;

    public TurmaDisciplinaControllerImpl() {
        turmaDisciplinaDAO = TurmaDisciplinaDAOImpl.getInstance();
    }

    @Override
    public void save(TurmaDisciplina turmaDisciplina) {
        this.turmaDisciplinaDAO.save(turmaDisciplina);

    }

    @Override
    public List<TurmaDisciplina> getAll() {
        return this.turmaDisciplinaDAO.getAll();
    }

    @Override
    public TurmaDisciplina getOne(long id) {
        return this.turmaDisciplinaDAO.getOne(id);
    }

    @Override
    public void update(TurmaDisciplina turmaDisciplina) {
        this.turmaDisciplinaDAO.update(turmaDisciplina);

    }

    @Override
    public void delete(long id) throws CustomException {
        this.turmaDisciplinaDAO.delete(id);

    }

    @Override
    public List<TurmaDisciplina> pesquisaTurma(TurmaDisciplinaFiltroVO turmaDisciplinaFiltroVO) {
        return this.turmaDisciplinaDAO.pesquisaTurma(turmaDisciplinaFiltroVO);
    }
}
