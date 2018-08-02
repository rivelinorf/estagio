package br.com.sonner.estagio.servlet.bairro;

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
import br.com.sonner.estagio.model.Bairro;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.model.Estado;

@WebServlet("/listaBairro")
public class BairroServlet extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws javax.servlet.ServletException, IOException {
		
		BairroControllerImpl bairroC = new BairroControllerImpl();
		
		List<Bairro> bairros = bairroC.getAll();
		
		request.setAttribute("listaBairro", bairros);

		RequestDispatcher rd = request.getRequestDispatcher("/bairro/lista.jsp");
		rd.forward(request, response);
		

	}
}