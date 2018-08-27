package br.com.sonner.estagio.servlet.usuario;

import br.com.sonner.estagio.controller.UsuarioControllerImpl;
import br.com.sonner.estagio.controller.api.UsuarioController;
import br.com.sonner.estagio.model.Usuario;
import br.com.sonner.estagio.vos.UsuarioFiltroVo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/usuario-logar")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsuarioController usuarioController = new UsuarioControllerImpl();
        Usuario user = new Usuario();
        HttpSession sessao = request.getSession();

        user.setUsuario(request.getParameter("usuario"));
        user.setSenha(request.getParameter("senha"));


        if (usuarioController.efetuaLogin(user) != null) {
            sessao.setAttribute("USER", user);
            response.sendRedirect("/views/home.jsp");

        } else {
            request.getSession().setAttribute("errors", "Usuário ou senha inválidos");
            response.sendRedirect("index.jsp");
        }

    }
}
