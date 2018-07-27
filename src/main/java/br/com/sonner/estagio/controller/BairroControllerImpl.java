package br.com.sonner.estagio.controller;

import br.com.sonner.estagio.controller.api.BairroController;
import br.com.sonner.estagio.dao.BairroDAOImpl;
import br.com.sonner.estagio.model.Bairro;

public class BairroControllerImpl implements BairroController {
	
	private BairroDAOImpl bDAO;
	
	

	@Override
	public void save(Bairro bairro) {
		new BairroDAOImpl().save(bairro);
		
	}

}
