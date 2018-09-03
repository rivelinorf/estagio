package br.com.sonner.estagio.controller.api;

import br.com.sonner.estagio.model.Endereco;
import br.com.sonner.estagio.vos.EnderecoFiltroVO;

import java.util.List;

public interface EnderecoController {
    void save(Endereco endereco);

    List<Endereco> getAll();

    Endereco getOne(long id);

    void update(Endereco endereco);

    void delete(long id);

    List<Endereco> filtrar(EnderecoFiltroVO enderecosPesquisados);

    List<String> validation(Endereco endereco);

    List<Endereco> filtrarLike(EnderecoFiltroVO enderecosPesquisados);
}
