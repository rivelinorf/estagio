package br.com.sonner.estagio.servlet.cidade;

import br.com.sonner.estagio.controller.CidadeControllerImpl;
import br.com.sonner.estagio.controller.EstadoControllerImpl;
import br.com.sonner.estagio.controller.api.CidadeController;
import br.com.sonner.estagio.controller.api.EstadoController;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.model.Estado;
import br.com.sonner.estagio.vos.CidadeFiltroVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/insere-cidade")
public class Insere extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        CidadeControllerImpl cidadeController = new CidadeControllerImpl();
        EstadoController estadoController = new EstadoControllerImpl();
        CidadeFiltroVO vo = new CidadeFiltroVO();
        HttpSession session = req.getSession();

        Estado estado = estadoController.getOne(Long.valueOf(req.getParameter("estado")));
        cidadeController.save(new Cidade(req.getParameter("nome"), req.getParameter("sigla"), req.getParameter("cep"), estado));

        vo.setNome("");
        vo.setSigla("");
        vo.setCep("");
        vo.setEstado(null);
        session.setAttribute("listaCidade", cidadeController.filtrar(vo));

        res.sendRedirect("/views/cidade/lista.jsp");
    }
}
