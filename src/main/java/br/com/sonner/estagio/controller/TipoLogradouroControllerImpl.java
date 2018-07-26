package br.com.sonner.estagio.controller;

import br.com.sonner.estagio.controller.api.TipoLogradouroController;
import br.com.sonner.estagio.dao.TipoLogradouroDAOImpl;
import br.com.sonner.estagio.model.TipoLogradouro;

public class TipoLogradouroControllerImpl implements TipoLogradouroController {

	@Override
	public void save(TipoLogradouro tipoLogradouro) {	
		new TipoLogradouroDAOImpl().save(tipoLogradouro);
	}

}
