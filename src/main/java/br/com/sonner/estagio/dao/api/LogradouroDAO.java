package br.com.sonner.estagio.dao.api;

import java.util.List;

import br.com.sonner.estagio.model.Logradouro;

public interface LogradouroDAO {

	void save(Logradouro Logradouro);

	List<Logradouro> get(Logradouro Logradouro);

	void update(Logradouro Logradouro);

	void delete(Logradouro Logradouro);

}
