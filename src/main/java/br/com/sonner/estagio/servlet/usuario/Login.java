package br.com.sonner.estagio.servlet.usuario;

import br.com.sonner.estagio.controller.UsuarioControllerImpl;
import br.com.sonner.estagio.controller.api.UsuarioController;
import br.com.sonner.estagio.model.Usuario;

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
        HttpSession sessao = request.getSession();

        String usuario = null;
        String senha = null;

        if (request.getParameter("usuario") != null) {
            usuario = request.getParameter("usuario");
        }

        if (request.getParameter("senha") != null) {
            senha = request.getParameter("senha");
        }


        Usuario user = new Usuario();
        user.setUsuario(usuario);
        user.setSenha(senha);

        //List<String> erros = usuarioController.validationLog(user);
        RequestDispatcher rd;
       // if (erros.size() == 0) {
            user = usuarioController.efetuaLogin(user);
            sessao.setAttribute("USER", user);
            rd = request.getRequestDispatcher("/views/home.jsp");
            rd.forward(request, response);
        /*} else {
            request.setAttribute("msg", "Usuário ou senha inválidos");
            rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }*/
    }
}
