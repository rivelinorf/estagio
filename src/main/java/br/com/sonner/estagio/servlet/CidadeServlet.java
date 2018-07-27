package br.com.sonner.estagio.servlet;

import br.com.sonner.estagio.controller.CidadeControllerImpl;
import br.com.sonner.estagio.controller.api.CidadeController;
import br.com.sonner.estagio.model.Cidade;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cidade")
public class CidadeServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) {
        CidadeController cidadeController = new CidadeControllerImpl();

//        cidadeController.save(new Cidade(req.getParameter("nome"), req.get));
    }
}
