package br.com.sonner.estagio.dao.api;

import java.util.List;

import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.model.Logradouro;
import br.com.sonner.estagio.model.TipoLogradouro;
import br.com.sonner.estagio.vos.LogradouroFiltroVO;

public interface LogradouroDAO {

	Logradouro save(Logradouro logradouro);

	List<Logradouro> getAll();
	
	Logradouro getOne(Long id);

	void update(Logradouro logradouro);

	void delete(Long id);
	
	List<Logradouro> pesquisaLogradouro(LogradouroFiltroVO vo);

}
