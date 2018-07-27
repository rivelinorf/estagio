package br.com.sonner.estagio.controller;

import br.com.sonner.estagio.controller.api.EnderecoController;
import br.com.sonner.estagio.model.Endereco;

public class EnderecoControllerImpl implements EnderecoController {

	public void save(Endereco endereco) {
		new EnderecoControllerImpl().save(endereco);

	}

}
