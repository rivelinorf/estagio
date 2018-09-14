package br.com.sonner.estagio.dao.api;

import br.com.sonner.estagio.model.Logradouro;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.LogradouroFiltroVO;

import java.util.List;

public interface LogradouroDAO {

    void save(Logradouro logradouro);

    List<Logradouro> getAll();

    Logradouro getOne(Long id);

    void update(Logradouro logradouro);

    void delete(Long id) throws CustomException;

    List<Logradouro> pesquisaLogradouro(LogradouroFiltroVO vo);

    List<Logradouro> pesquisaLogradouroLike(LogradouroFiltroVO vo);
}
