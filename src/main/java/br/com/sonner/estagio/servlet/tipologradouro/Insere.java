package br.com.sonner.estagio.servlet.tipologradouro;

import java.io.IOException;
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
        TipoLogradouroController tipoLogradouroController = new TipoLogradouroControllerImpl();

        novo.setNome(request.getParameter("nome"));

        tipoLogradouroController.save(novo);

        HttpSession session = request.getSession();
        TipologradouroFiltroVO vo = new TipologradouroFiltroVO();
        vo.setNome("");
        session.setAttribute("listaTipologradouro", ((TipoLogradouroControllerImpl) tipoLogradouroController).filtrar(vo));


        response.sendRedirect("/views/tipologradouro/lista.jsp");


    }
}


