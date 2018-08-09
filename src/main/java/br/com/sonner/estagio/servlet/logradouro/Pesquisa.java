package br.com.sonner.estagio.servlet.logradouro;

import br.com.sonner.estagio.controller.EstadoControllerImpl;
import br.com.sonner.estagio.controller.TipoLogradouroControllerImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/pesquisa-logradouro")
public class Pesquisa extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	TipoLogradouroControllerImpl tipoLogradouroController =new TipoLogradouroControllerImpl();
    	tipoLogradouroController.setTipoLogradourosPesquisados(request.getParameter("tipologradouro"));
    }
}
