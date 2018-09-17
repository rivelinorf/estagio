package br.com.sonner.estagio.dao.api;

import br.com.sonner.estagio.model.Bairro;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.BairroFiltroVO;

import java.util.List;

public interface BairroDAO {
    void save(Bairro bairro);

    List<Bairro> getAll();

    void update(Bairro bairro);

    void delete(Long id) throws CustomException;

    Bairro getOne(Long id);

    List<Bairro> pesquisaBairro(BairroFiltroVO vo);

    List<Bairro> pesquisaBairroLike(BairroFiltroVO vo);
}
