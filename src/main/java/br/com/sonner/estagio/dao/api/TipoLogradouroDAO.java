package br.com.sonner.estagio.dao.api;

import br.com.sonner.estagio.model.TipoLogradouro;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.TipologradouroFiltroVO;

import java.util.List;

public interface TipoLogradouroDAO {
    void save(TipoLogradouro tipoLogradouro);

    List<TipoLogradouro> getAll();

    TipoLogradouro getOne(Long id);

    void update(TipoLogradouro tipoLogradouro);

    void delete(Long id) throws CustomException;

    List<TipoLogradouro> pesquisaTipoLogradouro(String nome);

    List<TipoLogradouro> pesquisaTipoLogradouroLike(TipologradouroFiltroVO vo);
}
