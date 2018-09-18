package br.com.sonner.estagio.controller;

import br.com.sonner.estagio.controller.api.FuncionarioController;
import br.com.sonner.estagio.dao.FuncionarioDAOImpl;
import br.com.sonner.estagio.model.parte2.primeiro.Funcionario;
import br.com.sonner.estagio.util.CustomException;

import java.util.List;

public class FuncionarioControllerImpl implements FuncionarioController {

    private FuncionarioDAOImpl funcionarioDAO;

    public FuncionarioControllerImpl() {
        funcionarioDAO = FuncionarioDAOImpl.getInstance();
    }

    @Override
    public void save(Funcionario funcionario) {
        this.funcionarioDAO.save(funcionario);


    }

    @Override
    public List<Funcionario> getAll() {
        return this.funcionarioDAO.getAll();


    }

    @Override
    public Funcionario getOne(long id) {
        return this.funcionarioDAO.getOne(id);
    }

    @Override
    public void update(Funcionario funcionario) {
        this.funcionarioDAO.update(funcionario);

    }

    @Override
    public void delete(long id) throws CustomException {
        this.funcionarioDAO.delete(id);

    }
}
