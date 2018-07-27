package br.com.sonner.estagio.controller.api;

import java.util.List;

import br.com.sonner.estagio.model.Logradouro;

public interface LogradouroController {

	void save(Logradouro logradouro);

	List<Logradouro> getAll();

	Logradouro getOne(long id);

	void update(Logradouro logradouro);

	void delete(long id);

}
