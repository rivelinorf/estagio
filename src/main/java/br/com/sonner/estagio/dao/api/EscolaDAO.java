package br.com.sonner.estagio.dao.api;

import br.com.sonner.estagio.model.Bairro;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.model.Escola;
import br.com.sonner.estagio.model.Estado;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.EscolaFiltroVO;

import java.util.List;

public interface EscolaDAO {
    void save(Escola escola);

    List<Escola> getAll();

    void update(Escola escola);

    void delete(Long id) throws CustomException;

    Escola getOne(Long id);

    List<Escola> pesquisaEscola(EscolaFiltroVO vo);

    List<Escola> pesquisaEscolaLike(EscolaFiltroVO vo);

    List<Escola> pesquisaEscolaPorEstado(Estado estado);

    List<Escola> pesquisaEscolaPorCidade(Cidade cidade);

    List<Escola> pesquisaEscolaPorBairro(Bairro bairro);

}
