package br.com.sonner.estagio.controller;

import br.com.sonner.estagio.controller.api.TurmaController;
import br.com.sonner.estagio.dao.TurmaDAOImpl;
import br.com.sonner.estagio.model.Turma;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.TurmaFiltroVO;

import java.util.ArrayList;
import java.util.List;

public class TurmaControllerImpl implements TurmaController {
    private TurmaDAOImpl turmaDAO;

    public TurmaControllerImpl() {
        turmaDAO = TurmaDAOImpl.getInstance();
    }

    @Override
    public void save(Turma turma) {
        this.turmaDAO.save(turma);

    }

    @Override
    public List<Turma> getAll() {
        return this.turmaDAO.getAll();
    }

    @Override
    public Turma getOne(long id) {
        return this.turmaDAO.getOne(id);
    }

    @Override
    public void update(Turma turma) {
        this.turmaDAO.update(turma);

    }

    @Override
    public void delete(long id) throws CustomException {
        this.turmaDAO.delete(id);

    }

    @Override
    public List<String> validation(Turma turma) {
        List<String> erros = new ArrayList<>();

        if (turma.getNome() == null || turma.getNome().isEmpty()) {
            erros.add("O nome da turma não pode ser vazio");
        }


        if (turma.getNome().length() > 50) {
            erros.add("O nome da sala não pode exceder 15 caracteres  ");
        }


        if (turma.getEscola() == null) {
            erros.add("Impossível ter uma turma sem uma escola selecionada");
        }
        return erros;
    }

    public List<Turma> filtrar(TurmaFiltroVO vo) {
        return this.turmaDAO.pesquisaTurma(vo);
    }

    public List<Turma> filtrarLike(TurmaFiltroVO vo) {
        return this.turmaDAO.pesquisaTurmaLike(vo);

    }
}
