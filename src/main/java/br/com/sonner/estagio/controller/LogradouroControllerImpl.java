package br.com.sonner.estagio.controller;

import java.util.List;
import br.com.sonner.estagio.controller.api.LogradouroController;
import br.com.sonner.estagio.dao.CidadeDAOImpl;
import br.com.sonner.estagio.dao.LogradouroDAOImpl;
import br.com.sonner.estagio.dao.TipoLogradouroDAOImpl;
import br.com.sonner.estagio.dao.api.CidadeDAO;
import br.com.sonner.estagio.dao.api.LogradouroDAO;
import br.com.sonner.estagio.dao.api.TipoLogradouroDAO;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.model.Logradouro;
import br.com.sonner.estagio.model.TipoLogradouro;
import br.com.sonner.estagio.vos.CidadeFiltroVO;
import br.com.sonner.estagio.vos.LogradouroFiltroVO;

public class LogradouroControllerImpl implements LogradouroController {

	private LogradouroDAO lDAO;

	public LogradouroControllerImpl() {
		this.lDAO = LogradouroDAOImpl.getIntance();
	}

	@Override
	public void save(Logradouro logradouro) {
		this.lDAO.save(logradouro);
	}

	@Override
	public List<Logradouro> getAll() {
		return this.lDAO.getAll();
	}

	@Override
	public Logradouro getOne(long id) {
		return this.lDAO.getOne(id);
	}

	@Override
	public void update(Logradouro logradouro) {
		this.lDAO.update(logradouro);
	}

	@Override
	public void delete(long id) {
		this.lDAO.delete(id);

	}
	public List<Logradouro> filtrar (LogradouroFiltroVO vo) {
		return this.lDAO.pesquisaLogradouro(vo);

	}

}
