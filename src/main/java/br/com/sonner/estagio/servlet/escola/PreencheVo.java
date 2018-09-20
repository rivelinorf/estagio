package br.com.sonner.estagio.servlet.escola;

import br.com.sonner.estagio.controller.*;
import br.com.sonner.estagio.model.*;
import br.com.sonner.estagio.model.Escola;
import br.com.sonner.estagio.vos.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/escola/preenche-vo")
public class PreencheVo {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        EscolaControllerImpl escolaController = new EscolaControllerImpl();
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
        EscolaFiltroVO escolaFiltroVO = new EscolaFiltroVO();
        BairroControllerImpl bairroController = new BairroControllerImpl();
        EstadoControllerImpl estadoController = new EstadoControllerImpl();
        Endereco endereco = null;
        Escola escola = null;
        HttpSession session = request.getSession();

        if (request.getParameter("id") != null && request.getParameter("id") != "") {

            escola = escolaController.getOne(Long.valueOf(request.getParameter("id")));

            escolaFiltroVO.setNome(escola.getNome());
            escolaFiltroVO.setEndereco(escola.getEndereco().getId());

            endereco = enderecoController.getOne(escolaFiltroVO.getEndereco());

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
            session.setAttribute("escolaParaEditar", escolaFiltroVO);
            session.setAttribute("estado", estado);
            session.setAttribute("cidade", cidade);

            response.sendRedirect("/views/escola/atualiza.jsp");
        } else {

            session.setAttribute("filtroCidade_insereEndereco", null);
            session.setAttribute("listaCidade_insereEndereco", null);
            session.setAttribute("filtroLogradouro_insereEndereco", null);
            session.setAttribute("filtroBairro_insereEndereco", null);
            session.setAttribute("listaBairro_insereEndereco", null);
            session.setAttribute("enderecoParaInserir", null);
            session.setAttribute("escolaParaEditar", null);
            session.setAttribute("estado", null);
            session.setAttribute("cidade", null);

            response.sendRedirect("/views/escola/insere.jsp");

        }
    }
}
