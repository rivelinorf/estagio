package br.com.sonner.estagio.controller;

import br.com.sonner.estagio.controller.api.BairroController;
import br.com.sonner.estagio.model.Bairro;

public class BairroControllerImpl implements BairroController {

	@Override
	public void save(Bairro bairro) {
		new BairroControllerImpl().save(bairro);
		
	}

}
