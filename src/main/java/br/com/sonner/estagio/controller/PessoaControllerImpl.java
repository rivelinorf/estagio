package br.com.sonner.estagio.controller;

import br.com.sonner.estagio.controller.api.PessoaController;
import br.com.sonner.estagio.dao.PessoaDAOImpl;
import br.com.sonner.estagio.model.parte2.primeiro.Pessoa;
import br.com.sonner.estagio.util.CustomException;

import java.util.List;

public class PessoaControllerImpl implements PessoaController {

    private PessoaDAOImpl pessoaDAO;

    public PessoaControllerImpl() {
        pessoaDAO = PessoaDAOImpl.getInstance();
    }

    @Override
    public void save(Pessoa pessoa) {
        this.pessoaDAO.save(pessoa);

    }

    @Override
    public List<Pessoa> getAll() {
        return this.pessoaDAO.getAll();
    }

    @Override
    public Pessoa getOne(long id) {
        return this.pessoaDAO.getOne(id);
    }

    @Override
    public void update(Pessoa pessoa) {
        this.pessoaDAO.update(pessoa);

    }

    @Override
    public void delete(long id) throws CustomException {
        this.pessoaDAO.delete(id);

    }
}
