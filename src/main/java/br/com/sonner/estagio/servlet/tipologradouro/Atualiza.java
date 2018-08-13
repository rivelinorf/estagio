package br.com.sonner.estagio.servlet.tipologradouro;

import br.com.sonner.estagio.controller.EstadoControllerImpl;
import br.com.sonner.estagio.controller.TipoLogradouroControllerImpl;
import br.com.sonner.estagio.model.Estado;
import br.com.sonner.estagio.model.TipoLogradouro;
import br.com.sonner.estagio.vos.EstadoFiltroVO;
import br.com.sonner.estagio.vos.TipologradouroFiltroVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/atualiza-tipologradouro")
public class Atualiza extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        TipoLogradouroControllerImpl tipoLogradouroController = new TipoLogradouroControllerImpl();
        TipoLogradouro tipoLogradouro = new TipoLogradouro();

        tipoLogradouro.setId(Long.valueOf(request.getParameter("id")));
        tipoLogradouro.setNome(request.getParameter("tipologradouro"));

        tipoLogradouroController.update(tipoLogradouro);

        HttpSession session = request.getSession();
        TipologradouroFiltroVO vo = new TipologradouroFiltroVO();
        vo.setNome("");


        session.setAttribute("listaTipologradouro", tipoLogradouroController.filtrar(vo));

        response.sendRedirect("/views/tipologradouro/lista.jsp");
    }
}
