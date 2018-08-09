package br.com.sonner.estagio.controller.api;

import java.util.List;

import br.com.sonner.estagio.model.Bairro;

public interface BairroController {
	void save(Bairro bairro);
	List<Bairro> getAll();
	Bairro getOne(long id);
	void update(Bairro bairro);
	void delete(long id);
	List<Bairro> getBairrosPesquisados();
	void setBairrosPesquisados(String nome, long cidadeID);
	

}
