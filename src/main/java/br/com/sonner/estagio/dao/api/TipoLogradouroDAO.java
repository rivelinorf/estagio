package br.com.sonner.estagio.dao.api;

import java.util.List;

import br.com.sonner.estagio.model.TipoLogradouro;

public interface TipoLogradouroDAO {
	void save(TipoLogradouro tipoLogradouro);
	
	TipoLogradouro getOne(Long id);

	List<TipoLogradouro> getAll();

	void update(TipoLogradouro tipoLogradouro);

	void delete(Long id);
}
