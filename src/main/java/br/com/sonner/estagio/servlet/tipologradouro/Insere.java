package br.com.sonner.estagio.servlet.tipologradouro;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sonner.estagio.controller.TipoLogradouroControllerImpl;
import br.com.sonner.estagio.model.TipoLogradouro;
import br.com.sonner.estagio.vos.TipologradouroFiltroVO;

@WebServlet("/insere-tipologradouro")
public class Insere extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        TipoLogradouro aux = new TipoLogradouro();
        TipoLogradouroControllerImpl tipoLogradouroController = new TipoLogradouroControllerImpl();
        HttpSession session = request.getSession();
        TipologradouroFiltroVO vo = new TipologradouroFiltroVO();
        aux.setNome("");

        if (request.getParameter("nome") != "" && request.getParameter("nome") != null) {
            aux.setNome(request.getParameter("nome"));
        }

        List<String> erros = tipoLogradouroController.validation(aux);

        session.setAttribute("filtroTipologradouro", null);

        if (erros.size() == 0) {

            vo.setNome("");
            vo.setNome(aux.getNome());

            // List<TipoLogradouro> verifica = tipoLogradouroController.filtrar(vo);

            //if (verifica.size() == 0) {
            if (true) {
                tipoLogradouroController.save(aux);
                vo.setNome("");

                session.setAttribute("listaTipologradouro", (tipoLogradouroController.filtrar(vo)));
                session.setAttribute("success", "Tipo Logradouro inserido com sucesso");
                session.setAttribute("filtroTipologradouro", null);

                response.sendRedirect("/views/tipologradouro/lista.jsp");
            } else {
                String existe = "Tipo de logradouro já cadastrado!";

                session.setAttribute("errors", existe);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/tipologradouro/insere.jsp");
                requestDispatcher.forward(request, response);

            }
        } else {
            session.setAttribute("errors", erros);
            session.setAttribute("campoTipologradouro", aux);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/tipologradouro/insere.jsp");
            requestDispatcher.forward(request, response);
        }

    }
}
