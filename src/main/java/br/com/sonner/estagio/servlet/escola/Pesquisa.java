package br.com.sonner.estagio.servlet.escola;

import br.com.sonner.estagio.controller.*;
import br.com.sonner.estagio.controller.api.BairroController;
import br.com.sonner.estagio.controller.api.CidadeController;
import br.com.sonner.estagio.controller.api.EnderecoController;
import br.com.sonner.estagio.controller.api.EstadoController;
import br.com.sonner.estagio.model.*;
import br.com.sonner.estagio.vos.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        String nome;


        if (request.getParameter("estado") != "" && request.getParameter("estado") != null) {
            estado = estadoController.getOne(Long.valueOf(request.getParameter("estado")));
            session.setAttribute("listaCidade", estado.getCidades());
            session.setAttribute("estadoFiltro", estado);
            session.setAttribute("listaEscola", escolaController.pesquisaEscolaPorEstado(estado));

        }
        if (request.getParameter("cidade") != "" && request.getParameter("cidade") != null) {
            cidade = cidadeController.getOne(Long.valueOf(request.getParameter("cidade")));
            session.setAttribute("listaBairro", cidade.getBairros());
            session.setAttribute("cidadeFiltro", cidade);
            session.setAttribute("listaEscola", escolaController.pesquisaEscolaPorCidade(cidade));

        }
        if (request.getParameter("bairro") != "" && request.getParameter("bairro") != null) {
            bairro = bairroController.getOne(Long.valueOf(request.getParameter("bairro")));
            endereco.setBairro(bairro);
            session.setAttribute("bairroFiltro", bairro);
            session.setAttribute("listaEscola", escolaController.pesquisaEscolaPorBairro(bairro));
        }

        if (request.getParameter("nome") != null) {
            nome = request.getParameter("nome");
            escolaFiltroVO.setNome(nome);
            session.setAttribute("filtroEscola", escolaFiltroVO);
            if (bairro.getId() != null || cidade.getId() != null || estado.getId() != null) {
                List<Escola> escolas = (List<Escola>) session.getAttribute("listaEscola");
                List<Escola> listaEscola = new ArrayList<Escola>();
                for (int i = 0; i < escolas.size(); i++) {

                    if (escolas.get(i).getNome().equals(nome)) {

                        listaEscola.add(escolas.get(i));

                    }

                }
                session.setAttribute("listaEscola", listaEscola);
            } else {

                session.setAttribute("listaEscola", escolaController.filtrarLike(escolaFiltroVO));
            }
        }


        if (escolaFiltroVO.getNome() == null && estado.getId() == null && cidade.getId() == null && bairro.getId() == null) {
            session.setAttribute("filtroEscola", null);
            session.setAttribute("listaEscola", null);
            session.setAttribute("estado", null);
            session.setAttribute("cidade", null);
            session.setAttribute("bairro", null);
        }

        response.sendRedirect("/views/escola/lista.jsp");
    }
}
