package br.com.sonner.estagio.controller;

import java.util.List;

import br.com.sonner.estagio.controller.api.BairroController;
import br.com.sonner.estagio.dao.BairroDAOImpl;
import br.com.sonner.estagio.model.Bairro;

public class BairroControllerImpl implements BairroController {
	
	private BairroDAOImpl bDAO;
	private static List<Bairro> BAIRROS_PESQUISADOS;
	
	public BairroControllerImpl() {
		bDAO = BairroDAOImpl.getInstance();
	}
	
	@Override
	public void save(Bairro bairro) {
		this.bDAO.save(bairro);
	}

	@Override
	public List<Bairro> getAll() {
		return this.bDAO.getAll();
	}

	@Override
	public Bairro getOne(long id) {
		return this.bDAO.getOne(id);
	}

	@Override
	public void update(Bairro bairro) {
		this.bDAO.update(bairro);
		
	}

	@Override
	public void delete(long id) {
		this.bDAO.delete(id);
		
	}

	@Override
	public List<Bairro> getBairrosPesquisados() {
		return BAIRROS_PESQUISADOS;
	}

	@Override
	public void setBairrosPesquisados(String nome, long cidadeID) {
		BairroControllerImpl.BAIRROS_PESQUISADOS = this.bDAO.pesquisaBairro(nome, cidadeID);
		
	}
	
	

}
