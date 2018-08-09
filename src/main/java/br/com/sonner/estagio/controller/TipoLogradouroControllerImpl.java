package br.com.sonner.estagio.controller;

import java.util.List;

import br.com.sonner.estagio.controller.api.TipoLogradouroController;
import br.com.sonner.estagio.dao.TipoLogradouroDAOImpl;
import br.com.sonner.estagio.dao.api.TipoLogradouroDAO;
import br.com.sonner.estagio.model.TipoLogradouro;

public class TipoLogradouroControllerImpl implements TipoLogradouroController {
	private TipoLogradouroDAO tDAO;
	private static List<TipoLogradouro> TIPOLOGADOURO_PESQUISADOS;

	public TipoLogradouroControllerImpl() {
		this.tDAO = TipoLogradouroDAOImpl.getInstance();
	}

	@Override
	public void save(TipoLogradouro tipoLogradouro) {
		this.tDAO.save(tipoLogradouro);
	}

	@Override
	public List<TipoLogradouro> getAll() {
		return this.tDAO.getAll();
	}

	@Override
	public TipoLogradouro getOne(long id) {
		return this.tDAO.getOne(id);
	}

	@Override
	public void update(TipoLogradouro tipoLogradouro) {
		this.tDAO.update(tipoLogradouro);

	}

	@Override
	public void delete(long id) {
		this.tDAO.delete(id);
	}

	@Override
	public List<TipoLogradouro> getTipoLogradourosPesquisados() {
		return TIPOLOGADOURO_PESQUISADOS;
	}

	@Override
	public  void setTipoLogradourosPesquisados(String nome) {
        TipoLogradouroControllerImpl.TIPOLOGADOURO_PESQUISADOS=this.tDAO.pesquisaTipoLogradouro(nome);
	
	}

}
