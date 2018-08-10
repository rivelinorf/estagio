package br.com.sonner.estagio.controller;

import java.util.List;

import br.com.sonner.estagio.controller.api.TipoLogradouroController;
import br.com.sonner.estagio.dao.TipoLogradouroDAOImpl;
import br.com.sonner.estagio.dao.api.TipoLogradouroDAO;
import br.com.sonner.estagio.model.Estado;
import br.com.sonner.estagio.model.TipoLogradouro;
import br.com.sonner.estagio.vos.EstadoFiltroVO;
import br.com.sonner.estagio.vos.TipologradouroFiltroVO;

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
	
	  public List<TipoLogradouro> filtrar(TipologradouroFiltroVO tipologradourosPesquisados) {
	        return this.tDAO.pesquisaTipoLogradouro((tipologradourosPesquisados.getNome()));
	    }

}
