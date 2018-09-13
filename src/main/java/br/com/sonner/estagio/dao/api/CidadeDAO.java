package br.com.sonner.estagio.dao.api;

import java.util.List;

import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.CidadeFiltroVO;

public interface CidadeDAO {
    void save(Cidade cidade);

    List<Cidade> getAll();

    Cidade getOne(long id);

    void update(Cidade cidade);

    void delete(long id) throws CustomException;

    List<Cidade> pesquisaCidade(CidadeFiltroVO vo);

    List<Cidade> pesquisaCidadeLike(CidadeFiltroVO vo);
}
