package br.com.sonner.estagio.dao.api;

import java.util.List;

import br.com.sonner.estagio.model.Endereco;
import br.com.sonner.estagio.vos.EnderecoFiltroVO;

public interface EnderecoDAO {
	void save(Endereco endereco);

	List<Endereco> getAll();

	void update(Endereco endereco);

	void delete(Long id);

	Endereco getOne(Long id);
	
	List<Endereco> pesquisaEndereco(EnderecoFiltroVO vo);
}
