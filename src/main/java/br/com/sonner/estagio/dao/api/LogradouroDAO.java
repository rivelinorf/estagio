package br.com.sonner.estagio.dao.api;

import java.util.List;

import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.model.Logradouro;
import br.com.sonner.estagio.model.TipoLogradouro;

public interface LogradouroDAO {

	void save(Logradouro logradouro);

	List<Logradouro> getAll();
	
	Logradouro getOne(Long id);

	void update(Logradouro logradouro);

	void delete(Long id);
	
	List<Logradouro> pesquisaLogradouro(String nome,long cidadeID,long tipologradouroID);


}
