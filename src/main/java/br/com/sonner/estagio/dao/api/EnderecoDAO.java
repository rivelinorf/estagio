package br.com.sonner.estagio.dao.api;

import br.com.sonner.estagio.model.Endereco;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.EnderecoFiltroVO;

import java.util.List;

public interface EnderecoDAO {
    void save(Endereco endereco);

    List<Endereco> getAll();

    void update(Endereco endereco);

    void delete(Long id) throws CustomException ;

    Endereco getOne(Long id);

    List<Endereco> pesquisaEndereco(EnderecoFiltroVO vo);

    List<Endereco> pesquisaEnderecoLike(EnderecoFiltroVO vo);
}
