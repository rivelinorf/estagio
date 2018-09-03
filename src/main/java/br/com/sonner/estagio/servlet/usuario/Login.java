package br.com.sonner.estagio.servlet.usuario;

import br.com.sonner.estagio.controller.UsuarioControllerImpl;
import br.com.sonner.estagio.controller.api.UsuarioController;
import br.com.sonner.estagio.model.Usuario;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/usuario-logar")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UsuarioController usuarioController = new UsuarioControllerImpl();
        Usuario user = new Usuario();
        HttpSession sessao = request.getSession();

        user.setUsuario(request.getParameter("usuario"));
        user.setSenha(request.getParameter("senha"));


        Usuario usuario = usuarioController.efetuaLogin(user);

        if (usuario != null) {
            sessao.setAttribute("USER", usuario);
            response.sendRedirect("/views/home.jsp");

        } else {
            request.getSession().setAttribute("errors", "Usuário ou senha inválidos");
            response.sendRedirect("index.jsp");
        }

    }
}
