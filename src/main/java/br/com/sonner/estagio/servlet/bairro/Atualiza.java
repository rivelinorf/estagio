package br.com.sonner.estagio.servlet.bairro;

import br.com.sonner.estagio.controller.BairroControllerImpl;
import br.com.sonner.estagio.controller.CidadeControllerImpl;
import br.com.sonner.estagio.controller.EstadoControllerImpl;
import br.com.sonner.estagio.model.Bairro;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.model.Estado;
import br.com.sonner.estagio.vos.BairroFiltroVO;
import br.com.sonner.estagio.vos.CidadeFiltroVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet("/atualiza-bairro")
public class Atualiza extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        CidadeControllerImpl cidadeController = new CidadeControllerImpl();
        CidadeFiltroVO vo = new CidadeFiltroVO();
        Estado estado = new Estado();
        EstadoControllerImpl estadoController = new EstadoControllerImpl();

        vo.setCep("");
        vo.setEstado(null);
        vo.setId(null);
        vo.setNome("");
        vo.setCod("");

        HttpSession session = request.getSession();

        if (request.getParameter("estado") != "" && request.getParameter("estado") != null) {
            vo.setEstado(Long.valueOf(request.getParameter("estado")));
            estado = estadoController.getOne(vo.getEstado());

            session.setAttribute("filtroCidade_atualiza", vo);
            session.setAttribute("listaCidade_atualiza", cidadeController.filtrar(vo));
            session.setAttribute("estado", estado);

        }

        if (vo.getEstado() == null) {

            session.setAttribute("listaBairro", null);
            session.setAttribute("filtroCidade_atualiza", null);
            session.setAttribute("listaCidade_atualiza", null);

            session.setAttribute("estado",null);

        }

        response.sendRedirect("/views/bairro/atualiza.jsp");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        CidadeControllerImpl cidadeController = new CidadeControllerImpl();
        BairroControllerImpl bairroController = new BairroControllerImpl();
        Bairro bairro = new Bairro();
        BairroFiltroVO vo = new BairroFiltroVO();
        CidadeFiltroVO cidadevo = new CidadeFiltroVO();
        HttpSession session = req.getSession();

        String nome = req.getParameter("nome");

        Cidade cidade = null;

        vo.setNome("");
        vo.setCidade(null);
        vo.setId(null);

        if (req.getParameter("estadoSession") != "" && req.getParameter("estadoSession") != null) {
            cidadevo.setEstado(Long.valueOf(req.getParameter("estadoSession")));
        }

        if (req.getParameter("cidade") != "") {
            cidade = cidadeController.getOne(Long.valueOf(req.getParameter("cidade")));
            cidadevo.setNome(cidade.getNome());
            cidadevo.setId(cidade.getId());
            cidadevo.setCod(cidade.getCod());
            cidadevo.setEstado(cidade.getEstado().getId());
            cidade.setCep(cidade.getCep());
            bairro.setCidade(cidade);
            vo.setCidade(bairro.getCidade().getId());
        }

        bairro.setId(Long.valueOf(req.getParameter("id")));
        bairro.setNome(nome);


        List<String> erros = bairroController.validation(bairro);


        vo.setNome(bairro.getNome());
        vo.setId(bairro.getId());

        if (erros.size() == 0) {

            List<Bairro> verifica = bairroController.filtrar(vo);
            if (verifica.size() == 0) {

                bairroController.update(bairro);

                vo.setCidade(null);
                vo.setNome("");
                session.setAttribute("listaBairro", bairroController.filtrar(vo));
                session.setAttribute("success", "Bairro atualizado com sucesso");
                session.setAttribute("filtroBairro", null);
                res.sendRedirect("/views/bairro/lista.jsp");

            } else {

                BairroFiltroVO bairroantigo = (BairroFiltroVO) session.getAttribute("bairroParaEditar");

                if (vo.getCidade().equals(bairroantigo.getCidade()) && vo.getNome().equals(bairroantigo.getNome())) {
                    vo.setCidade(null);
                    vo.setNome("");
                    session.setAttribute("listaBairro", bairroController.filtrar(vo));
                    session.setAttribute("filtroBairro", null);
                    res.sendRedirect("/views/bairro/lista.jsp");

                } else {

                    String existe = "Bairro já cadastrado!";

                    session.setAttribute("errors", existe);
                    session.setAttribute("filtroCidade_atualiza", cidadevo);
                    session.setAttribute("bairroParaEditar", vo);
                    session.setAttribute("listaCidade_atualiza", cidadeController.filtrar(cidadevo));
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/bairro/atualiza.jsp");
                    requestDispatcher.forward(req, res);
                }
            }
        } else {
            session.setAttribute("errors", erros);
            session.setAttribute("filtroCidade_atualiza", cidadevo);
            session.setAttribute("bairroParaEditar", vo);
            session.setAttribute("listaCidade_atualiza", cidadeController.filtrar(cidadevo));
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/bairro/atualiza.jsp");
            requestDispatcher.forward(req, res);
        }

    }

}
