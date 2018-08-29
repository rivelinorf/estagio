package br.com.sonner.estagio.servlet.usuario;

import br.com.sonner.estagio.controller.TokenControllerImpl;
import br.com.sonner.estagio.controller.UsuarioControllerImpl;
import br.com.sonner.estagio.model.Usuario;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/redefinir-senha")
public class RedefSenha extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UsuarioControllerImpl usuarioController = new UsuarioControllerImpl();

        String newPass = request.getParameter("newPass");
        String confirmPass = request.getParameter("confirmPass");

        List<String> erros = usuarioController.redefSenha(newPass, confirmPass);

        if (erros.size() == 0) {
            TokenControllerImpl tokenController = new TokenControllerImpl();
            Usuario newUser = tokenController.getUser(request.getParameter("token"));

            newUser.setSenha(confirmPass);

            usuarioController.update(newUser);
            request.getSession().setAttribute("success", "Senha alterada com sucesso");
            response.sendRedirect("/index.jsp");
        } else {
            request.getSession().setAttribute("errors", erros);
            response.sendRedirect("/redefinir.jsp");
        }
    }
}
