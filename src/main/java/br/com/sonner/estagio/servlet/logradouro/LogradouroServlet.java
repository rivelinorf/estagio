package br.com.sonner.estagio.servlet.logradouro;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.Request;

import br.com.sonner.estagio.controller.BairroControllerImpl;
import br.com.sonner.estagio.controller.EstadoControllerImpl;
import br.com.sonner.estagio.controller.LogradouroControllerImpl;
import br.com.sonner.estagio.model.Bairro;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.model.Estado;
import br.com.sonner.estagio.model.Logradouro;

@WebServlet("/lista-logradouro")
public class LogradouroServlet extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws javax.servlet.ServletException, IOException {
		
		LogradouroControllerImpl logradouroC = new LogradouroControllerImpl();
		
		List<Logradouro> logradouro = logradouroC.getAll();
		
		request.setAttribute("listalogradouro", logradouro);

		RequestDispatcher rd = request.getRequestDispatcher("/views/logradouro/lista.jsp");
		rd.forward(request, response);
		

	}
}