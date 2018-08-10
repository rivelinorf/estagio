package br.com.sonner.estagio.controller;

import java.util.List;

import br.com.sonner.estagio.controller.api.BairroController;
import br.com.sonner.estagio.dao.BairroDAOImpl;
import br.com.sonner.estagio.dao.CidadeDAOImpl;
import br.com.sonner.estagio.dao.api.CidadeDAO;
import br.com.sonner.estagio.model.Bairro;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.vos.BairroFiltroVO;

public class BairroControllerImpl implements BairroController {
	
	private BairroDAOImpl bDAO;
	
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
	public List<Bairro> filtrar(BairroFiltroVO bairrosPesquisados) {
		CidadeDAO cDAO = CidadeDAOImpl.getInstance();
		Cidade cidade = cDAO.getOne(bairrosPesquisados.getCidade());
		return this.bDAO.pesquisaBairro(bairrosPesquisados.getNome(), cidade);
	}


	
	

}
