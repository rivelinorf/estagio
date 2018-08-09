package br.com.sonner.estagio.controller.api;

import java.util.List;

import br.com.sonner.estagio.model.Estado;
import br.com.sonner.estagio.model.TipoLogradouro;

public interface TipoLogradouroController {
	void save(TipoLogradouro tipoLogradouro);

	List<TipoLogradouro> getAll();

	TipoLogradouro getOne(long id);

	void update(TipoLogradouro tipoLogradouro);

	void delete(long id);

	List<TipoLogradouro> getTipoLogradourosPesquisados();

	void setTipoLogradourosPesquisados(String nome);

}
