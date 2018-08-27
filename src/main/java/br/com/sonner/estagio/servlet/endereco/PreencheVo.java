package br.com.sonner.estagio.servlet.endereco;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sonner.estagio.controller.BairroControllerImpl;
import br.com.sonner.estagio.controller.CidadeControllerImpl;
import br.com.sonner.estagio.controller.EnderecoControllerImpl;
import br.com.sonner.estagio.controller.LogradouroControllerImpl;
import br.com.sonner.estagio.model.Bairro;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.model.Endereco;
import br.com.sonner.estagio.model.Logradouro;
import br.com.sonner.estagio.vos.BairroFiltroVO;
import br.com.sonner.estagio.vos.CidadeFiltroVO;
import br.com.sonner.estagio.vos.EnderecoFiltroVO;
import br.com.sonner.estagio.vos.LogradouroFiltroVO;

@WebServlet("/endereco/preenche-vo")
public class PreencheVo extends HttpServlet {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EnderecoControllerImpl enderecoController = new EnderecoControllerImpl();
        LogradouroControllerImpl logradouroController = new LogradouroControllerImpl();
        LogradouroFiltroVO logradourovo = new LogradouroFiltroVO();
        BairroControllerImpl bairroControllerImpl = new BairroControllerImpl();
        BairroFiltroVO bairrovo = new BairroFiltroVO();
        CidadeControllerImpl cidadeController = new CidadeControllerImpl();
        CidadeFiltroVO cidadevo = new CidadeFiltroVO();
        EnderecoFiltroVO vo = new EnderecoFiltroVO();
        BairroControllerImpl bairroController = new BairroControllerImpl();
        Endereco endereco = new Endereco();
        HttpSession session = request.getSession();

        endereco = enderecoController.getOne(Long.valueOf(request.getParameter("id")));

        vo.setId(endereco.getId());
        vo.setNumero(endereco.getNumero());
        vo.setCep(endereco.getCep());
        vo.setComplemento(endereco.getComplemento());
        vo.setBairro(endereco.getBairro().getId());
        vo.setLogradouro(endereco.getLogradouro().getId());

        Logradouro logradouro = logradouroController.getOne(endereco.getLogradouro().getId());
        Bairro bairro = bairroControllerImpl.getOne(endereco.getBairro().getId());
        Cidade cidade = cidadeController.getOne(endereco.getLogradouro().getCidade().getId());

        cidadevo.setId(cidade.getId());
        cidadevo.setNome(cidade.getNome());
        cidadevo.setCep(cidade.getCep());
        cidadevo.setCod(cidade.getCod());
        cidadevo.setEstado(cidade.getEstado().getId());

        logradourovo.setId(logradouro.getId());
        logradourovo.setNome(logradouro.getNome());
        logradourovo.setCidade(logradouro.getCidade().getId());
        logradourovo.setTipologradouro(logradouro.getTipologradouro().getId());

        bairrovo.setId(bairro.getId());
        bairrovo.setNome(bairro.getNome());
        bairrovo.setCidade(bairro.getCidade().getId());

        session.setAttribute("filtroCidade_atualizaEndereco", cidadevo);
        session.setAttribute("listaCidade_atualizaEndereco", cidadeController.filtrar(cidadevo));
        session.setAttribute("filtroLogradouro_atualizaEndereco", logradourovo);
        session.setAttribute("filtroBairro_atualizaEndereco", bairrovo);
        session.setAttribute("listaBairro_atualizaEndereco", bairroController.filtrar(bairrovo));
        session.setAttribute("enderecoParaEditar", vo);

        response.sendRedirect("/views/endereco/atualiza.jsp");
    }

}
