package br.com.sonner.estagio.dao.api;

import java.util.List;

import br.com.sonner.estagio.model.Bairro;
import br.com.sonner.estagio.model.Cidade;

public interface BairroDAO {
	void save(Bairro bairro);
	List<Bairro> getAll();
	void update (Bairro bairro);
	void delete(long id);
	Bairro getOne(long id);
	List<Bairro> pesquisaBairro(String nome, Cidade cidade);
}
