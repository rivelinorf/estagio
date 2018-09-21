package br.com.sonner.estagio.controller;

import br.com.sonner.estagio.controller.api.EscolaController;
import br.com.sonner.estagio.dao.EscolaDAOImpl;
import br.com.sonner.estagio.model.Bairro;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.model.Escola;
import br.com.sonner.estagio.model.Estado;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.EscolaFiltroVO;

import java.util.ArrayList;
import java.util.List;

public class EscolaControllerImpl implements EscolaController {
    private EscolaDAOImpl escolaDAO;

    public EscolaControllerImpl() {
        escolaDAO = EscolaDAOImpl.getInstance();
    }

    @Override
    public void save(Escola escola) {
        this.escolaDAO.save(escola);

    }

    @Override
    public List<Escola> getAll() {
        return this.escolaDAO.getAll();
    }

    @Override
    public Escola getOne(long id) {
        return this.escolaDAO.getOne(id);
    }

    @Override
    public void update(Escola escola) {
        this.escolaDAO.update(escola);

    }

    @Override
    public void delete(long id) throws CustomException {
        this.escolaDAO.delete(id);

    }

    @Override
    public List<String> validation(Escola escola) {
        List<String> erros = new ArrayList<>();

        if (escola.getNome().length() == 0) {
            erros.add("Não é possivel ter uma escola sem nome");
        }

        if (escola.getNome().length() > 50) {
            erros.add("Nome da escola não pode exceder 50 caracteres");
        }

        if (escola.getEndereco() == null) {
            erros.add("Impossível ter uma escola sem endereço");
        }

        return erros;
    }

    @Override
    public List<Escola> filtrar(EscolaFiltroVO vo) {
        return this.escolaDAO.pesquisaEscola(vo);
    }

    @Override
    public List<Escola> filtrarLike(EscolaFiltroVO vo) {
        return this.escolaDAO.pesquisaEscolaLike(vo);
    }

    @Override
    public List<Escola> pesquisaEscolaPorEstado(Estado estado) {
        return this.escolaDAO.pesquisaEscolaPorEstado(estado);
    }

    @Override
    public List<Escola> pesquisaEscolaPorCidade(Cidade cidade) {
        return this.escolaDAO.pesquisaEscolaPorCidade(cidade);
    }

    @Override
    public List<Escola> pesquisaEscolaPorBairro(Bairro bairro) {
        return this.escolaDAO.pesquisaEscolaPorBairro(bairro);
    }
}
