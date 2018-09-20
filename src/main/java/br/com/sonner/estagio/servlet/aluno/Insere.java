package br.com.sonner.estagio.servlet.aluno;

import br.com.sonner.estagio.controller.*;
import br.com.sonner.estagio.controller.api.EscolaController;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.model.Escola;
import br.com.sonner.estagio.model.Estado;
import br.com.sonner.estagio.model.Turma;
import br.com.sonner.estagio.vos.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/insere-aluno")
public class Insere extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        EscolaControllerImpl escolaController = new EscolaControllerImpl();
        EscolaFiltroVO escolaFiltroVO = new EscolaFiltroVO();

        TurmaControllerImpl turmaController = new TurmaControllerImpl();
        TurmaFiltroVO turmaFiltroVO = new TurmaFiltroVO();

        Escola escola;
        Turma turma;

        AlunoFiltroVO alunoFiltroVO = new AlunoFiltroVO();

        escolaFiltroVO.setEndereco(null);
        escolaFiltroVO.setNome("");


        CidadeControllerImpl cidadeController = new CidadeControllerImpl();
        CidadeFiltroVO cidadevo = new CidadeFiltroVO();

        BairroControllerImpl bairroController = new BairroControllerImpl();
        BairroFiltroVO bairrovo = new BairroFiltroVO();

        Estado estado;
        Cidade cidade;
        EstadoControllerImpl estadoController = new EstadoControllerImpl();


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

        response.sendRedirect("/views/endereco/insere.jsp");
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {


    }
}
