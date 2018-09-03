package br.com.sonner.estagio.dao.api;

import br.com.sonner.estagio.model.Bairro;
import br.com.sonner.estagio.vos.BairroFiltroVO;

import java.util.List;

public interface BairroDAO {
    void save(Bairro bairro);

    List<Bairro> getAll();

    void update(Bairro bairro);

    void delete(long id);

    Bairro getOne(long id);

    List<Bairro> pesquisaBairro(BairroFiltroVO vo);

    List<Bairro> pesquisaBairroLike(BairroFiltroVO vo);

}
