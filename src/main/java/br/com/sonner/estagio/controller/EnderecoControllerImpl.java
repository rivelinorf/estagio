package br.com.sonner.estagio.controller;

import br.com.sonner.estagio.controller.api.EnderecoController;
import br.com.sonner.estagio.dao.EnderecoDAOImpl;
import br.com.sonner.estagio.model.Endereco;
import br.com.sonner.estagio.vos.EnderecoFiltroVO;

import java.util.ArrayList;
import java.util.List;

public class EnderecoControllerImpl implements EnderecoController {

    private EnderecoDAOImpl eDAO;

    public EnderecoControllerImpl() {
        eDAO = EnderecoDAOImpl.getInstance();
    }

    @Override
    public void save(Endereco endereco) {
        this.eDAO.save(endereco);

    }

    @Override
    public List<Endereco> getAll() {
        return this.eDAO.getAll();
    }

    @Override
    public Endereco getOne(long id) {
        return this.eDAO.getOne(id);
    }

    @Override
    public void update(Endereco endereco) {
        this.eDAO.update(endereco);

    }

    @Override
    public void delete(long id) {
        this.eDAO.delete(id);

    }

    @Override
    public List<Endereco> filtrar(EnderecoFiltroVO vo) {
        return this.eDAO.pesquisaEndereco(vo);
    }

    @Override
    public List<String> validation(Endereco endereco) {
        List<String> erros = new ArrayList<>();

        String aux = "";

        if (endereco.getNumero() != null) {
            aux = Integer.toString(endereco.getNumero());
        }

        if (aux.length() == 0) {
            erros.add("Não é possível ter um endereço sem número");
        }

        if (aux.length() == 10) {
            erros.add("Número não pode exceder 4 caracteres");
        }

        if (endereco.getCep().length() < 10 && endereco.getCep().length() > 0) {
            erros.add("CEP inválido");
        }

        if (endereco.getCep().length() == 0) {
            erros.add("Não é possível ter um endereço sem CEP");
        }

        if (endereco.getBairro() == null) {
            erros.add("Impossível ter uma endereço sem bairro");
        }

        if (endereco.getLogradouro() == null) {
            erros.add("Impossível ter uma endereço sem logradouro");
        }

        return erros;

    }

    @Override
    public List<Endereco> filtrarLike(EnderecoFiltroVO vo) {
        return this.eDAO.pesquisaEnderecoLike(vo);
    }

}
