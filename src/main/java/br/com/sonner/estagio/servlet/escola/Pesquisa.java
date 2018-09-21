package br.com.sonner.estagio.servlet.escola;

import br.com.sonner.estagio.controller.*;
import br.com.sonner.estagio.controller.api.BairroController;
import br.com.sonner.estagio.controller.api.CidadeController;
import br.com.sonner.estagio.controller.api.EnderecoController;
import br.com.sonner.estagio.controller.api.EstadoController;
import br.com.sonner.estagio.model.Bairro;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.model.Endereco;
import br.com.sonner.estagio.model.Estado;
import br.com.sonner.estagio.vos.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/pesquisa-escola")
public class Pesquisa extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        EscolaControllerImpl escolaController = new EscolaControllerImpl();
        EscolaFiltroVO escolaFiltroVO = new EscolaFiltroVO();
        EstadoController estadoController = new EstadoControllerImpl();
        EstadoFiltroVO estadoFiltroVO = new EstadoFiltroVO();
        CidadeController cidadeController = new CidadeControllerImpl();
        CidadeFiltroVO cidadeFiltroVO = new CidadeFiltroVO();
        BairroController bairroController = new BairroControllerImpl();
        BairroFiltroVO bairroFiltroVO = new BairroFiltroVO();
        EnderecoController enderecoController = new EnderecoControllerImpl();
        EnderecoFiltroVO enderecoFiltroVO = new EnderecoFiltroVO();
        Estado estado = new Estado();
        Cidade cidade = new Cidade();
        Bairro bairro = new Bairro();
        Endereco endereco = new Endereco();
        HttpSession session = request.getSession();

        escolaFiltroVO.setNome(request.getParameter("nome"));

        if (request.getParameter("estado") != "" && request.getParameter("estado") != null) {
            estado = estadoController.getOne(Long.valueOf(request.getParameter("estado")));
            session.setAttribute("listaCidade", estado.getCidades());
            session.setAttribute("estado", estado);
            session.setAttribute("listaEscola",escolaController.pesquisaEscolaPorEstado(estado));

        }
        if (request.getParameter("cidade") != "" && request.getParameter("cidade") != null) {
            cidade = cidadeController.getOne(Long.valueOf(request.getParameter("cidade")));
            session.setAttribute("listaBairro", cidade.getBairros());
            session.setAttribute("cidade", cidade);
            session.setAttribute("listaEscola",escolaController.pesquisaEscolaPorCidade(cidade));

        }
        if (request.getParameter("bairro") != "" && request.getParameter("bairro") != null) {
            bairro = bairroController.getOne(Long.valueOf(request.getParameter("bairro")));
            endereco.setBairro(bairro);
            session.setAttribute("bairro", bairro);
            session.setAttribute("listaEscola",escolaController.pesquisaEscolaPorCidade(cidade));
        }



        if (escolaFiltroVO.getNome() == null && estado.getId() == null && cidade.getId() == null && bairro.getId() == null) {
            session.setAttribute("filtroEscola", null);
            session.setAttribute("listaEscola", null);
            session.setAttribute("estado", null);
        } else {
            session.setAttribute("filtroEscola", escolaFiltroVO);
            session.setAttribute("listaEscola", escolaController.filtrarLike(escolaFiltroVO));
        }

        response.sendRedirect("/views/escola/lista.jsp");
    }
}
