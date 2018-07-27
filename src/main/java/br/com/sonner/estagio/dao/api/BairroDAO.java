package br.com.sonner.estagio.dao.api;

import java.util.List;
import br.com.sonner.estagio.model.Bairro;

public interface BairroDAO {
	void save(Bairro bairro);
	List<Bairro> getAll();
	void update (Bairro bairro);
	void delete(Long id);
	Bairro getOne(Long id);
}
