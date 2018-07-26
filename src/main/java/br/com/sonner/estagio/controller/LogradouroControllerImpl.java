package br.com.sonner.estagio.controller;

import br.com.sonner.estagio.controller.api.LogradouroController;
import br.com.sonner.estagio.dao.LogradouroDAOImpl;
import br.com.sonner.estagio.model.Logradouro;

public class LogradouroControllerImpl implements LogradouroController {

	@Override
	public void save(Logradouro logradouro) {
        new LogradouroDAOImpl().save(logradouro);
		
	}

}
