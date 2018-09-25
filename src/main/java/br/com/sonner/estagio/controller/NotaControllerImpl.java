package br.com.sonner.estagio.controller;

import br.com.sonner.estagio.controller.api.NotaController;
import br.com.sonner.estagio.dao.NotaDAOImpl;
import br.com.sonner.estagio.model.Nota;
import br.com.sonner.estagio.model.TurmaDisciplina;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.NotaFiltroVO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class NotaControllerImpl implements NotaController {
    private NotaDAOImpl notaDAO;

    public NotaControllerImpl() {
        notaDAO = NotaDAOImpl.getInstance();
    }

    @Override
    public void save(Nota nota) {
        this.notaDAO.save(nota);

    }

    @Override
    public List<Nota> getAll() {
        return this.notaDAO.getAll();
    }

    @Override
    public Nota getOne(long id) {
        return this.notaDAO.getOne(id);
    }

    @Override
    public void update(Nota nota) {
        this.notaDAO.update(nota);

    }

    @Override
    public void delete(long id) throws CustomException {
        this.notaDAO.delete(id);

    }

    @Override
    public List<String> validation(Nota nota) {
        List<String> erros = new ArrayList<>();

        if (nota.getAluno() == null) {
            erros.add("Impossível inserir nota sem aluno");
        }

        if (nota.getNota() == null) {
            erros.add("Impossível inserir nota sem nota");
        }

        if (nota.getTurmaDisciplina().getDisciplina() == null) {
            erros.add("Impossível inserir nota sem diciplina");
        }

        if (nota.getTurmaDisciplina().getTurma() == null) {
            erros.add("Impossível inserir nota sem turma");
        }
        BigDecimal menor = new BigDecimal(0);
        BigDecimal maior = new BigDecimal(100);

        if (menor.compareTo(nota.getNota()) == 1 || nota.getNota().compareTo(maior) == 1) {
            erros.add("Nota inválida");
        }

        return erros;
    }

    @Override
    public List<Nota> pesquisaNota(NotaFiltroVO notaFiltroVO) {
        return this.notaDAO.pesquisaNota(notaFiltroVO);
    }
}
