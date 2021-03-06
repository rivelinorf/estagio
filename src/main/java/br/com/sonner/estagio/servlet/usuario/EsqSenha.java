package br.com.sonner.estagio.servlet.usuario;

import br.com.sonner.estagio.controller.TokenControllerImpl;
import br.com.sonner.estagio.controller.UsuarioControllerImpl;
import br.com.sonner.estagio.model.Usuario;
import br.com.sonner.estagio.vos.UsuarioFiltroVo;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

@WebServlet("/esqueci-senha")
public class EsqSenha extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UsuarioControllerImpl usuarioController = new UsuarioControllerImpl();
        UsuarioFiltroVo usuarioFiltroVo = new UsuarioFiltroVo();

        String usuario = request.getParameter("user");

        usuarioFiltroVo.setUsuario(usuario);

        List<Usuario> users = usuarioController.filtrar(usuarioFiltroVo);

        if (users.size() > 0 && usuario != "") {
            try {
                String uniqueID = UUID.randomUUID().toString();
                String host = "smtp.gmail.com";
                String user = "sge.sonner.contact@gmail.com";
                String pass = "sonner123";
                String to = users.get(0).getEmail();
                String subject = "Email de redefinir senha";


                String messageText = "Olá, seu link para redefinir a senha é: " + request.getRequestURL().toString().replace(request.getRequestURI().toString(), "") + "/redefinir.jsp?token=" + uniqueID;

                Properties props = System.getProperties();

                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", host);
                props.put("mail.smtp.port", "587");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.required", "true");

                java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
                Session mailSession = Session.getDefaultInstance(props, null);
                Message msg = new MimeMessage(mailSession);
                InternetAddress[] address = {new InternetAddress(to)};
                msg.setRecipients(Message.RecipientType.TO, address);
                msg.setSubject(subject);
                msg.setSentDate(new Date());
                msg.setText(messageText);

                Transport transport = mailSession.getTransport("smtp");
                transport.connect(host, user, pass);
                transport.sendMessage(msg, msg.getAllRecipients());
                transport.close();

                TokenControllerImpl tokenController = new TokenControllerImpl();
                tokenController.save(users.get(0), uniqueID);

                request.getSession().setAttribute("success", "Email enviado com sucesso");
            } catch (Exception ex) {
                System.out.println(ex);
            }

            response.sendRedirect("/senha.jsp");
        } else {
            request.getSession().setAttribute("errors", "Usuario não encontrado");
            response.sendRedirect("/senha.jsp");
        }
    }
}
