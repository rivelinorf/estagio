package br.com.sonner.estagio.servlet.endereco;

import br.com.sonner.estagio.controller.*;
import br.com.sonner.estagio.controller.api.EnderecoController;
import br.com.sonner.estagio.model.*;
import br.com.sonner.estagio.vos.BairroFiltroVO;
import br.com.sonner.estagio.vos.CidadeFiltroVO;
import br.com.sonner.estagio.vos.EnderecoFiltroVO;
import br.com.sonner.estagio.vos.LogradouroFiltroVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/busca-endereco")
public class BuscaCep extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        CidadeControllerImpl cidadeController = new CidadeControllerImpl();
        CidadeFiltroVO cidadevo = new CidadeFiltroVO();
        BairroControllerImpl bairroController = new BairroControllerImpl();
        BairroFiltroVO bairrovo = new BairroFiltroVO();
        Estado estado;
        Cidade cidade;
        Endereco endereco;
        EstadoControllerImpl estadoController = new EstadoControllerImpl();
        LogradouroControllerImpl logradouroController = new LogradouroControllerImpl();
        EnderecoController enderecoController = new EnderecoControllerImpl();
        EnderecoFiltroVO enderecovo = new EnderecoFiltroVO();

        LogradouroFiltroVO logradourovo = new LogradouroFiltroVO();

        cidadevo.setEstado(null);
        cidadevo.setNome("");
        cidadevo.setCod("");
        cidadevo.setCep("");
        cidadevo.setId(null);

        enderecovo.setBairro(null);
        enderecovo.setCep("");
        enderecovo.setComplemento("");
        enderecovo.setNumero(null);
        enderecovo.setLogradouro(null);
        enderecovo.setId(null);

        bairrovo.setCidade(null);
        bairrovo.setNome("");
        bairrovo.setId(null);

        logradourovo.setNome("");
        logradourovo.setCidade(null);
        logradourovo.setTipologradouro(null);

        HttpSession session = request.getSession();

        if (request.getParameter("cep") != "" && request.getParameter("cep") != null) {
            enderecovo.setCep(request.getParameter("cep"));
            List<Endereco> enderecos = enderecoController.filtrar(enderecovo);
            if (enderecos.size() > 0) {
                endereco = enderecos.get(0);
                cidade = endereco.getLogradouro().getCidade();
                estado = endereco.getLogradouro().getCidade().getEstado();


                if (enderecos.size() >= 1 & !cidade.getCep().equals(endereco.getCep())) {

                    enderecovo.setCep(endereco.getCep());

                    Logradouro logradouro = logradouroController.getOne(endereco.getLogradouro().getId());
                    Bairro bairro = bairroController.getOne(endereco.getBairro().getId());
                    estado = estadoController.getOne(cidade.getEstado().getId());

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

                    session.setAttribute("filtroCidade_insereEndereco", cidadevo);
                    session.setAttribute("listaCidade_insereEndereco", cidadeController.filtrar(cidadevo));
                    session.setAttribute("filtroLogradouro_insereEndereco", logradourovo);
                    session.setAttribute("filtroBairro_insereEndereco", bairrovo);
                    session.setAttribute("listaBairro_insereEndereco", bairroController.filtrar(bairrovo));
                    session.setAttribute("enderecoParaInserir", enderecovo);
                    session.setAttribute("estado", estado);
                    session.setAttribute("cidade", cidade);
                    session.setAttribute("validaCep", 1);

                } else if (enderecos.size() >= 1 & cidade.getCep().equals(endereco.getCep())) {
                    bairrovo.setCidade(cidade.getId());
                    bairrovo.setId(null);

                    cidadevo.setEstado(cidade.getEstado().getId());

                    enderecovo.setBairro(bairrovo.getId());
                    enderecovo.setCep(endereco.getCep());

                    session.setAttribute("enderecoParaInserir", enderecovo);
                    session.setAttribute("filtroLogradouro_insereEndereco", logradourovo);
                    session.setAttribute("filtroCidade_insereEndereco", cidadevo);
                    session.setAttribute("listaCidade_insereEndereco", cidadeController.filtrar(cidadevo));
                    session.setAttribute("filtroBairro_insereEndereco", bairrovo);
                    session.setAttribute("listaBairro_insereEndereco", bairroController.filtrar(bairrovo));
                    session.setAttribute("cidade", cidade);
                    session.setAttribute("estado", estado);
                    session.setAttribute("validaCep", 2);


                }
            } else {
                String existe = "CEP não encontrado!";

                session.setAttribute("errors", existe);

                session.setAttribute("enderecoParaInserir", enderecovo);
                session.setAttribute("filtroLogradouro_insereEndereco", null);
                session.setAttribute("filtroCidade_insereEndereco", null);
                session.setAttribute("listaCidade_insereEndereco", null);
                session.setAttribute("filtroBairro_insereEndereco", null);
                session.setAttribute("listaBairro_insereEndereco", null);
                session.setAttribute("cidade", null);
                session.setAttribute("estado", null);
                session.setAttribute("validaCep", 3);

            }
        } else {
            if (request.getParameter("estado") != "" && request.getParameter("estado") != null) {
                cidadevo.setEstado(Long.valueOf(request.getParameter("estado")));
                estado = estadoController.getOne(cidadevo.getEstado());

                session.setAttribute("filtroCidade_insereEndereco", cidadevo);
                session.setAttribute("listaCidade_insereEndereco", cidadeController.filtrar(cidadevo));
                session.setAttribute("listaBairro_insereEndereco", null);
                session.setAttribute("filtroBairro_insereEndereco", null);
                session.setAttribute("estado", estado);

            }

            if (request.getParameter("cidade") != "" && request.getParameter("cidade") != null) {
                Long id = Long.valueOf(request.getParameter("cidade"));
                cidade = cidadeController.getOne(id);

                bairrovo.setCidade(cidade.getId());

                cidadevo.setEstado(cidade.getEstado().getId());

                enderecovo.setBairro(bairrovo.getId());


                session.setAttribute("filtroCidade_insereEndereco", cidadevo);
                session.setAttribute("listaCidade_insereEndereco", cidadeController.filtrar(cidadevo));
                session.setAttribute("filtroBairro_insereEndereco", bairrovo);
                session.setAttribute("listaBairro_insereEndereco", bairroController.filtrar(bairrovo));
                session.setAttribute("cidade", cidade);

            }

            if (cidadevo.getEstado() == null && bairrovo.getCidade() == null) {

                if (session.getAttribute("cidade") == null) {
                    session.setAttribute("estado", null);
                    session.setAttribute("filtroCidade_insereEndereco", null);
                }

                session.setAttribute("cidade", null);
                session.setAttribute("filtroBairro_insereEndereco", null);
                session.setAttribute("listaBairro_insereEndereco", null);

            }
        }

        response.sendRedirect(request.getHeader("Referer"));
    }
}
