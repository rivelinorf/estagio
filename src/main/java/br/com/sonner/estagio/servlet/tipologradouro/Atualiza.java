package br.com.sonner.estagio.servlet.tipologradouro;

import br.com.sonner.estagio.controller.TipoLogradouroControllerImpl;
import br.com.sonner.estagio.model.TipoLogradouro;
import br.com.sonner.estagio.vos.TipologradouroFiltroVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/atualiza-tipologradouro")
public class Atualiza extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        TipoLogradouroControllerImpl tipoLogradouroController = new TipoLogradouroControllerImpl();
        HttpSession session = request.getSession();
        TipologradouroFiltroVO vo = new TipologradouroFiltroVO(), voSession = (TipologradouroFiltroVO) session.getAttribute("tipologradouro-para-editar");
        TipoLogradouro tipoLogradouro = new TipoLogradouro();

        if (voSession.getNome().equals(request.getParameter("tipologradouro"))) {
            session.setAttribute("filtroTipologradouro", null);
            response.sendRedirect("/views/tipologradouro/lista.jsp");
            return;
        }

        tipoLogradouro.setNome(request.getParameter("tipologradouro"));

        tipoLogradouro.setId(Long.valueOf(request.getParameter("id")));

        List<String> erros = tipoLogradouroController.validation(tipoLogradouro);

        if (erros.size() == 0) {

            vo.setNome("");
            vo.setNome(tipoLogradouro.getNome());

            List<TipoLogradouro> verifica = tipoLogradouroController.filtrar(vo);

            if (verifica.size() == 0) {

                tipoLogradouroController.update(tipoLogradouro);
                vo.setNome("");

                session.setAttribute("listaTipologradouro", tipoLogradouroController.filtrar(vo));
                session.setAttribute("filtroTipologradouro", null);

                response.sendRedirect("/views/tipologradouro/lista.jsp");
            } else {
                String existe = "Tipo de logradouro já cadastrado!";

                session.setAttribute("errors", existe);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/tipologradouro/atualiza.jsp");
                requestDispatcher.forward(request, response);
            }

        } else {
            session.setAttribute("errors", erros);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/tipologradouro/atualiza.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
