package br.com.sonner.estagio.servlet.cidade;

import br.com.sonner.estagio.controller.CidadeControllerImpl;
import br.com.sonner.estagio.controller.EstadoControllerImpl;
import br.com.sonner.estagio.controller.api.CidadeController;
import br.com.sonner.estagio.controller.api.EstadoController;
import br.com.sonner.estagio.model.Cidade;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cidade-atualiza")
public class Atualiza extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CidadeController cidadeController = new CidadeControllerImpl();
        EstadoController estadoController = new EstadoControllerImpl();
        Cidade novaCidade = new Cidade();

        novaCidade.setId(Long.valueOf(request.getParameter("id")));
        novaCidade.setNome(request.getParameter("nome"));
        novaCidade.setCep(request.getParameter("cep"));
        novaCidade.setCod(request.getParameter("codigo"));
        novaCidade.setEstado(estadoController.getOne(Long.valueOf(request.getParameter("estado"))));

        cidadeController.update(novaCidade);

        Cidade teste = cidadeController.getOne(Long.valueOf(request.getParameter("id")));
        System.out.printf(teste.getNome());

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/cidade/lista.jsp");
        requestDispatcher.forward(request, response);
    }
}
