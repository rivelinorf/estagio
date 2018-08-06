package br.com.sonner.estagio.servlet.tipologradouro;

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
import br.com.sonner.estagio.controller.TipoLogradouroControllerImpl;
import br.com.sonner.estagio.model.Bairro;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.model.Estado;
import br.com.sonner.estagio.model.TipoLogradouro;

@WebServlet("/listatipologradouro")
public class TipoLogradouroServlet extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws javax.servlet.ServletException, IOException {
		
		TipoLogradouroControllerImpl tipoLogradouroC = new TipoLogradouroControllerImpl();
		
		List<TipoLogradouro> tipologradouro = tipoLogradouroC.getAll();
		
		request.setAttribute("listatipologradouro", tipologradouro);

		RequestDispatcher rd = request.getRequestDispatcher("/views/tipologradouro/lista.jsp");
		rd.forward(request, response);
		

	}
}