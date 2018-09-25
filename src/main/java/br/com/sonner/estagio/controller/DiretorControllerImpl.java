package br.com.sonner.estagio.controller;

import br.com.sonner.estagio.controller.api.DiretorController;
import br.com.sonner.estagio.dao.DiretorDAOImpl;
import br.com.sonner.estagio.model.Diretor;
import br.com.sonner.estagio.model.Diretor;
import br.com.sonner.estagio.model.Funcionario;
import br.com.sonner.estagio.model.Pessoa;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.DiretorFiltroVO;

import java.util.ArrayList;
import java.util.List;

public class DiretorControllerImpl implements DiretorController {
    private DiretorDAOImpl diretorDAO;

    public DiretorControllerImpl() {
        diretorDAO = DiretorDAOImpl.getInstance();
    }

    @Override
    public void save(Diretor diretor) {
        this.diretorDAO.save(diretor);

    }

    @Override
    public List<Diretor> getAll() {
        return this.diretorDAO.getAll();
    }

    @Override
    public Diretor getOne(long id) {
        return this.diretorDAO.getOne(id);
    }

    @Override
    public void update(Diretor diretor) {
        this.diretorDAO.update(diretor);

    }

    @Override
    public void delete(long id) throws CustomException {
        this.diretorDAO.delete(id);

    }

    public List<Diretor> filtrar(DiretorFiltroVO diretorsPesquisados) {
        return this.diretorDAO.pesquisaDiretor(diretorsPesquisados);
    }

    public List<Diretor> filtrarLike(DiretorFiltroVO diretorsPesquisados) {
        return this.diretorDAO.pesquisaDiretorLike(diretorsPesquisados);
    }

    public List<String> validation(Diretor diretor) {
        List<String> erros = new ArrayList<>();

        if (diretor.getFuncionario().getPessoa().getNome().length() == 0) {
            erros.add("Não é possível ter um diretor sem nome");
        }

        if (diretor.getFuncionario().getPessoa().getNome().length() > 50) {
            erros.add("Nome do diretor não pode exceder 50 caracteres");
        }

        DiretorFiltroVO diretorFiltroVO = new DiretorFiltroVO();

        diretorFiltroVO.setFuncionario(new Funcionario());
        diretorFiltroVO.getFuncionario().setPessoa(new Pessoa());
        diretorFiltroVO.getFuncionario().getPessoa().setCpf(diretor.getFuncionario().getPessoa().getCpf());

        if (filtrar(diretorFiltroVO).size() > 0) {
            erros.add("CPF já cadastrador");
        }

        // regex para cpf

        return erros;
    }
}
