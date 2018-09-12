package br.com.sonner.estagio.servlet.bairro;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sonner.estagio.controller.BairroControllerImpl;
import br.com.sonner.estagio.controller.CidadeControllerImpl;
import br.com.sonner.estagio.controller.EstadoControllerImpl;
import br.com.sonner.estagio.controller.api.EstadoController;
import br.com.sonner.estagio.model.Bairro;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.model.Estado;
import br.com.sonner.estagio.vos.BairroFiltroVO;
import br.com.sonner.estagio.vos.CidadeFiltroVO;


@WebServlet("/insere-bairro")
public class Insere extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        CidadeControllerImpl cidadeController = new CidadeControllerImpl();
        CidadeFiltroVO vo = new CidadeFiltroVO();
        EstadoController ec = new EstadoControllerImpl();
        Estado estado = new Estado();

        vo.setCep("");
        vo.setEstado(null);
        vo.setId(null);
        vo.setNome("");
        vo.setCod("");

        HttpSession session = request.getSession();

        if (request.getParameter("estado") != "" && request.getParameter("estado") != null) {
            vo.setEstado(Long.valueOf(request.getParameter("estado")));
            estado = ec.getOne(vo.getEstado());

            session.setAttribute("filtroCidade_insereBairro", vo);
            session.setAttribute("listaCidade_insereBairro", cidadeController.filtrar(vo));
            session.setAttribute("estado", estado);

        }

        if (vo.getEstado() == null) {

            session.setAttribute("listaBairro", null);
            session.setAttribute("filtroCidade_insereBairro", null);
            session.setAttribute("listaCidade_insereBairro", null);
            session.setAttribute("bairroParaInserir", null);
            session.setAttribute("estado",null);

        }

        response.sendRedirect("/views/bairro/insere.jsp");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws javax.servlet.ServletException, IOException {

        BairroControllerImpl bairroController = new BairroControllerImpl();
        CidadeControllerImpl cidadeController = new CidadeControllerImpl();
        BairroFiltroVO vo = new BairroFiltroVO();
        HttpSession session = req.getSession();
        Cidade cidade = null;
        CidadeFiltroVO cidadevo = new CidadeFiltroVO();
        Bairro bairro = new Bairro();

        String nome = req.getParameter("nome");
        bairro.setNome(nome);

        if (req.getParameter("estadoSession") != "" && req.getParameter("estadoSession") != null) {
            cidadevo.setEstado(Long.valueOf(req.getParameter("estadoSession")));
        }

        if (req.getParameter("cidadeID") != "") {
            cidade = cidadeController.getOne(Long.valueOf(req.getParameter("cidadeID")));
            bairro.setCidade(cidade);
            vo.setCidade(bairro.getCidade().getId());


            cidadevo.setNome(cidade.getNome());
            cidadevo.setCod(cidade.getCod());
            cidadevo.setCep(cidade.getCep());
            cidadevo.setId(cidade.getId());

        }

        List<String> erros = bairroController.validation(bairro);

        vo.setNome("");
        vo.setCidade(null);
        vo.setId(null);

        vo.setId(bairro.getId());
        vo.setNome(bairro.getNome());

        if (erros.size() == 0) {

            List<Bairro> verifica = bairroController.filtrar(vo);

            if (verifica.size() == 0) {

                bairroController.save(bairro);

                vo.setNome("");
                vo.setCidade(null);
                vo.setId(null);

                cidadevo.setEstado(null);
                cidadevo.setNome("");
                cidadevo.setCod("");
                cidadevo.setCep("");
                cidadevo.setId(null);

                session.setAttribute("listaBairro", bairroController.filtrar(vo));
                session.setAttribute("filtroCidade_insereBairro", cidadevo);
                session.setAttribute("listaCidade_insereBairro", cidadeController.filtrar(cidadevo));
                session.setAttribute("success", "Bairro inserido com sucesso");
                session.setAttribute("filtroBairro", null);

                res.sendRedirect("/views/bairro/lista.jsp");
            } else {

                String existe = "Bairro já cadastrado!";

                session.setAttribute("errors", existe);
                session.setAttribute("filtroCidade_insereBairro", cidadevo);
                session.setAttribute("bairroParaInserir", vo);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/bairro/insere.jsp");
                requestDispatcher.forward(req, res);
            }
        } else {
            session.setAttribute("errors", erros);
            session.setAttribute("filtroCidade_insereBairro", cidadevo);
            session.setAttribute("bairroParaInserir", vo);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/bairro/insere.jsp");
            requestDispatcher.forward(req, res);
        }

    }
}
