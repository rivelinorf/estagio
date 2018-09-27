package br.com.sonner.estagio.controller;

import br.com.sonner.estagio.controller.api.PessoaController;
import br.com.sonner.estagio.dao.PessoaDAOImpl;
import br.com.sonner.estagio.model.Endereco;
import br.com.sonner.estagio.model.Pessoa;
import br.com.sonner.estagio.util.CustomException;

import java.util.ArrayList;
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

    @Override
    public List<String> validation(Pessoa pessoa) {
        List<String> erros = new ArrayList<>();

        if (pessoa.getNome() ==null || pessoa.getNome().isEmpty()) {
            erros.add("Nao é possivel inserir um aluno sem nome");
        }
        if (pessoa.getNome().length() > 50) {
            erros.add("O nome do aluno não pode exceder 50 caracteres  ");
        }
        if (pessoa.getDataNascimento().equals("")) {
            erros.add("Nao é possivel inserir um aluno sem data de nascimento");
        }
        if (pessoa.getCpf().isEmpty()) {
            erros.add("Nao é possivel inserir um aluno sem data de nascimento");
        }

        return erros;
    }
}
