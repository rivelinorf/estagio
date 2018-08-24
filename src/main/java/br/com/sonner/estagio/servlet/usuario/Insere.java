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

@WebServlet("/usuario-insere")
public class Insere extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        UsuarioController usuarioController = new UsuarioControllerImpl();
        HttpSession session = req.getSession();


        String email = null;
        String usuario = null;
        String senha = null;


        if (req.getParameter("email") != null && !req.getParameter("email").isEmpty()) {
            email = (req.getParameter("email"));
        }

        if (req.getParameter("usuario") != null && !req.getParameter("usuario").isEmpty()) {
            usuario = req.getParameter("usuario");

        }

        if (req.getParameter("senha") != null && !req.getParameter("senha").isEmpty()) {
            senha = req.getParameter("senha");

        }

        Usuario novousuario = new Usuario();

        novousuario.setEmail(email);
        novousuario.setUsuario(usuario);
        novousuario.setSenha(senha);


        List<String> erros = usuarioController.validation(novousuario);

        if (erros.size() == 0) {
            usuarioController.save(novousuario);

            session.setAttribute("success", "Usuario cadastrado com sucesso");
            res.sendRedirect("/index.jsp");
        } else {
            session.setAttribute("errors", erros);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/cadastro.jsp");
            requestDispatcher.forward(req, res);
        }

    }
}
