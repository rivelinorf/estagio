package br.com.sonner.estagio.controller;

import java.util.List;

import br.com.sonner.estagio.controller.api.LogradouroController;
import br.com.sonner.estagio.dao.LogradouroDAOImpl;
import br.com.sonner.estagio.dao.api.LogradouroDAO;
import br.com.sonner.estagio.model.Logradouro;

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

}
