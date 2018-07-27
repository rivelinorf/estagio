package br.com.sonner.estagio.servlet.cidade;

import br.com.sonner.estagio.controller.CidadeControllerImpl;
import br.com.sonner.estagio.controller.EstadoControllerImpl;
import br.com.sonner.estagio.controller.api.CidadeController;
import br.com.sonner.estagio.controller.api.EstadoController;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.model.Estado;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cidade")
public class CidadeServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) {
        CidadeController cidadeController = new CidadeControllerImpl();
        EstadoController estadoController = new EstadoControllerImpl();
        Estado estado = estadoController.getOne(Long.valueOf(req.getParameter("estado")));

        cidadeController.save(new Cidade(req.getParameter("nome"), req.getParameter("cod"), req.getParameter("cep"), estado));
    }
}
