package br.com.sonner.estagio.dao.api;

import java.util.List;

import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.vos.CidadeFiltroVO;

public interface CidadeDAO {
    void save(Cidade estado);

    List<Cidade> getAll();

    Cidade getOne(long id);

    void update(Cidade estado);

    void delete(long id);

    List<Cidade> pesquisaCidade(CidadeFiltroVO vo);

    List<Cidade> pesquisaCidadeLike(CidadeFiltroVO vo);
}
