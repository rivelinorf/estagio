package br.com.sonner.estagio.servlet.endereco;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sonner.estagio.controller.*;
import br.com.sonner.estagio.model.*;
import br.com.sonner.estagio.vos.BairroFiltroVO;
import br.com.sonner.estagio.vos.CidadeFiltroVO;
import br.com.sonner.estagio.vos.EnderecoFiltroVO;
import br.com.sonner.estagio.vos.LogradouroFiltroVO;

@WebServlet("/endereco/preenche-vo")
public class PreencheVo extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        EnderecoControllerImpl enderecoController = new EnderecoControllerImpl();
        LogradouroControllerImpl logradouroController = new LogradouroControllerImpl();
        LogradouroFiltroVO logradourovo = new LogradouroFiltroVO();
        BairroControllerImpl bairroControllerImpl = new BairroControllerImpl();
        BairroFiltroVO bairrovo = new BairroFiltroVO();
        BairroFiltroVO bairrovo2 = new BairroFiltroVO();
        CidadeControllerImpl cidadeController = new CidadeControllerImpl();
        CidadeFiltroVO cidadevo = new CidadeFiltroVO();
        CidadeFiltroVO cidadevo2 = new CidadeFiltroVO();
        EnderecoFiltroVO vo = new EnderecoFiltroVO();
        BairroControllerImpl bairroController = new BairroControllerImpl();
        EstadoControllerImpl estadoController = new EstadoControllerImpl();
        Endereco endereco;
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
        Estado estado = estadoController.getOne(cidade.getEstado().getId());

        cidadevo.setId(cidade.getId());
        cidadevo.setNome(cidade.getNome());
        cidadevo.setCep(cidade.getCep());
        cidadevo.setCod(cidade.getCod());
        cidadevo.setEstado(cidade.getEstado().getId());

        cidadevo2.setEstado(cidade.getEstado().getId());

        logradourovo.setId(logradouro.getId());
        logradourovo.setNome(logradouro.getNome());
        logradourovo.setCidade(logradouro.getCidade().getId());
        logradourovo.setTipologradouro(logradouro.getTipologradouro().getId());

        bairrovo.setId(bairro.getId());
        bairrovo.setNome(bairro.getNome());
        bairrovo.setCidade(bairro.getCidade().getId());

        bairrovo2.setCidade(bairro.getCidade().getId());

        session.setAttribute("filtroCidade_atualizaEndereco", cidadevo);
        session.setAttribute("listaCidade_atualizaEndereco", cidadeController.filtrar(cidadevo2));
        session.setAttribute("filtroLogradouro_atualizaEndereco", logradourovo);
        session.setAttribute("filtroBairro_atualizaEndereco", bairrovo);
        session.setAttribute("listaBairro_atualizaEndereco", bairroController.filtrar(bairrovo2));
        session.setAttribute("enderecoParaEditar", vo);
        session.setAttribute("estado", estado);
        session.setAttribute("cidade", cidade);

        response.sendRedirect("/views/endereco/atualiza.jsp");
    }

}
