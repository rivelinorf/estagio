package br.com.sonner.estagio.controller.api;

import br.com.sonner.estagio.model.Bairro;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.model.Escola;
import br.com.sonner.estagio.model.Estado;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.EscolaFiltroVO;

import java.util.List;

public interface EscolaController {
    void save(Escola escola);

    List<Escola> getAll();

    Escola getOne(long id);

    void update(Escola escola);

    void delete(long id) throws CustomException;

    List<String> validation(Escola escola);

    List<Escola> filtrar(EscolaFiltroVO bairrosPesquisados);

    List<Escola> filtrarLike(EscolaFiltroVO bairrosPesquisados);

    List<Escola> pesquisaEscolaPorEstado(Estado estado);

    List<Escola> pesquisaEscolaPorCidade(Cidade cidade);

    List<Escola> pesquisaEscolaPorBairro(Bairro bairro);
}
