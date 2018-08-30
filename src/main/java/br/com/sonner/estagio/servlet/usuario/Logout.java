package br.com.sonner.estagio.servlet.usuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/usuario-logout")
public class Logout extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sessao = request.getSession();
        sessao.removeAttribute("USER");
        sessao.invalidate();

        System.out.println("Sessão terminada");

        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);

    }

}
