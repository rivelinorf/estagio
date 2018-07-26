package br.com.sonner.estagio.dao.api;

import java.util.List;

import br.com.sonner.estagio.model.TipoLogradouro;

public interface TipoLogradouroDAO {
	void save(TipoLogradouro tipoLogradouro);
	
	 void getone(Long id);

	List<TipoLogradouro> get();

	void update(TipoLogradouro tipoLogradouro);

	void delete(Long id);
}
