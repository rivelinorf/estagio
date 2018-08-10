package br.com.sonner.estagio.controller;

import java.util.List;

import br.com.sonner.estagio.controller.api.EnderecoController;
import br.com.sonner.estagio.dao.EnderecoDAOImpl;
import br.com.sonner.estagio.model.Endereco;
import br.com.sonner.estagio.vos.EnderecoFiltroVO;

public class EnderecoControllerImpl implements EnderecoController {

	private EnderecoDAOImpl eDAO;
	private static List<Endereco> ENDERECOS_PESQUISADOS;

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
	public List<Endereco> filtrar(EnderecoFiltroVO enderecosPesquisados) {
		return this.eDAO.pesquisaEndereco(enderecosPesquisados.getCep());
	}


}
