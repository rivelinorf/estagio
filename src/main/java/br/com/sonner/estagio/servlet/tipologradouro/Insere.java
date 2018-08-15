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
import br.com.sonner.estagio.controller.api.TipoLogradouroController;
import br.com.sonner.estagio.model.TipoLogradouro;
import br.com.sonner.estagio.vos.TipologradouroFiltroVO;

@SuppressWarnings("serial")
@WebServlet("/insere-tipologradouro")
public class Insere extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, IOException {
        TipoLogradouro novo = new TipoLogradouro();
        TipoLogradouroControllerImpl tipoLogradouroController = new TipoLogradouroControllerImpl();
        HttpSession session = request.getSession();
        TipologradouroFiltroVO vo = new TipologradouroFiltroVO();


        novo.setNome(request.getParameter("nome"));
        List<String> erros = tipoLogradouroController.validation(novo);

        if (erros.size() == 0) {
            tipoLogradouroController.save(novo);
            vo.setNome("");

            session.setAttribute("listaTipologradouro", ( tipoLogradouroController.filtrar(vo)));
            session.setAttribute("success", "Tipo Logradouro inserido com sucesso");

            response.sendRedirect("/views/tipologradouro/lista.jsp");
        } else {
            session.setAttribute("errors", erros);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/tipologradouro/insere.jsp");
            requestDispatcher.forward(request, response);
        }


    }
}


