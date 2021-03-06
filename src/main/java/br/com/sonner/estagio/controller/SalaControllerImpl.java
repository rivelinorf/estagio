package br.com.sonner.estagio.controller;

import br.com.sonner.estagio.controller.api.SalaController;
import br.com.sonner.estagio.dao.SalaDAOImpl;
import br.com.sonner.estagio.model.Sala;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.SalaFiltroVO;

import java.util.ArrayList;
import java.util.List;

public class SalaControllerImpl implements SalaController {
    private SalaDAOImpl salaDAO;

    public SalaControllerImpl() {
        salaDAO = SalaDAOImpl.getInstance();
    }

    @Override
    public void save(Sala sala) {
        this.salaDAO.save(sala);

    }

    @Override
    public List<Sala> getAll() {
        return this.salaDAO.getAll();
    }

    @Override
    public Sala getOne(long id) {
        return this.salaDAO.getOne(id);
    }

    @Override
    public void update(Sala sala) {
        this.salaDAO.update(sala);

    }

    @Override
    public void delete(long id) throws CustomException {
        this.salaDAO.delete(id);

    }

    @Override
    public List<String> validation(Sala sala) {
        List<String> erros = new ArrayList<>();

        if (sala.getNome() == null || sala.getNome().isEmpty()) {
            erros.add("O nome da sala não pode ser vazio");
        }


        if (sala.getNome().length() > 50) {
            erros.add("O nome da sala não pode exceder 15 caracteres  ");
        }


        if (sala.getEscola() == null) {
            erros.add("Impossível ter uma sala sem uma escola selecionada");
        }
        return erros;
    }

    public List<Sala> filtrar(SalaFiltroVO vo) {
        return this.salaDAO.pesquisaSala(vo);

    }

    public List<Sala> filtrarLike(SalaFiltroVO vo) {
        return this.salaDAO.pesquisaSalaLike(vo);
    }


}
