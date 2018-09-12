package br.com.sonner.estagio.dao.api;

import br.com.sonner.estagio.model.Logradouro;
import br.com.sonner.estagio.vos.LogradouroFiltroVO;

import java.util.List;

public interface LogradouroDAO {

    Logradouro save(Logradouro logradouro);

    List<Logradouro> getAll();

    Logradouro getOne(Long id);

    void update(Logradouro logradouro);

    void delete(Long id);

    List<Logradouro> pesquisaLogradouro(LogradouroFiltroVO vo);

    List<Logradouro> pesquisaLogradouroLike(LogradouroFiltroVO vo);
}
