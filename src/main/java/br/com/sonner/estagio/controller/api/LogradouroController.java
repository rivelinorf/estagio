package br.com.sonner.estagio.controller.api;

import java.util.List;

import br.com.sonner.estagio.model.Logradouro;
import br.com.sonner.estagio.vos.LogradouroFiltroVO;

public interface LogradouroController {

	void save(Logradouro logradouro);

	List<Logradouro> getAll();

	Logradouro getOne(long id);

	void update(Logradouro logradouro);

	void delete(long id);
	
	List<Logradouro> filtrar(LogradouroFiltroVO logradouroPesquisados);


}

