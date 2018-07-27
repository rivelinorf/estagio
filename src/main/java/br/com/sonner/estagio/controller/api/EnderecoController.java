package br.com.sonner.estagio.controller.api;

import java.util.List;

import br.com.sonner.estagio.model.Endereco;

public interface EnderecoController {
	void save(Endereco endereco);
	List<Endereco> getAll();
	Endereco getOne(long id);
	void update(Endereco endereco);
	void delete(long id);
	

}
