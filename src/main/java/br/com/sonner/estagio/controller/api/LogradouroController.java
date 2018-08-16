package br.com.sonner.estagio.controller.api;

import java.util.List;

import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.model.Logradouro;
import br.com.sonner.estagio.vos.LogradouroFiltroVO;

public interface LogradouroController {

	Logradouro save(Logradouro logradouro);

	List<Logradouro> getAll();

	Logradouro getOne(long id);

	void update(Logradouro logradouro);

	void delete(long id);

	List<String> validation(Logradouro logradouro);
}

