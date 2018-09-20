package br.com.sonner.estagio.dao.api;

import br.com.sonner.estagio.model.Sala;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.SalaFiltroVO;

import java.util.List;

public interface SalaDAO {
    void save(Sala sala);

    List<Sala> getAll();

    void update(Sala sala);

    void delete(Long id) throws CustomException;

    Sala getOne(Long id);

    List<Sala> pesquisaSalaLike(SalaFiltroVO vo);

    List<Sala> pesquisaSala(SalaFiltroVO vo);
}
