package br.com.sonner.estagio.servlet.cidade;

import br.com.sonner.estagio.controller.CidadeControllerImpl;
import br.com.sonner.estagio.controller.EstadoControllerImpl;
import br.com.sonner.estagio.controller.api.CidadeController;
import br.com.sonner.estagio.controller.api.EstadoController;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.vos.CidadeFiltroVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/atualiza-cidade")
public class Atualiza extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CidadeControllerImpl cidadeController = new CidadeControllerImpl();
        CidadeFiltroVO vo = new CidadeFiltroVO();
        EstadoController estadoController = new EstadoControllerImpl();
        Cidade novaCidade = new Cidade();

        novaCidade.setId(Long.valueOf(request.getParameter("id")));
        novaCidade.setNome(request.getParameter("cidade"));
        novaCidade.setCep(request.getParameter("cep"));
        novaCidade.setCod(request.getParameter("sigla"));
        novaCidade.setEstado(estadoController.getOne(Long.valueOf(request.getParameter("estado"))));

        cidadeController.update(novaCidade);

        vo.setNome("");
        vo.setSigla("");
        vo.setCep("");
        vo.setEstado(null);

        HttpSession session = request.getSession();
        session.setAttribute("listaCidade", cidadeController.filtrar(vo));

        response.sendRedirect("/views/cidade/lista.jsp");
    }
}
