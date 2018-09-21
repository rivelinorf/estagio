package br.com.sonner.estagio.servlet.diretor;

import br.com.sonner.estagio.controller.DiretorControllerImpl;
import br.com.sonner.estagio.model.Pessoa;
import br.com.sonner.estagio.vos.DiretorFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;

@WebServlet("/pesquisa-diretor")
public class Pesquisa extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        DiretorControllerImpl diretorController = new DiretorControllerImpl();
        DiretorFiltroVO vo = new DiretorFiltroVO();
        HttpSession session = request.getSession();

        Pessoa pessoa = new Pessoa();
        pessoa.setNome(request.getParameter("nome"));

//        vo.set

        if (true) { // primeira vez entrando na pagina
            session.setAttribute("filtroDiretor", null);
            session.setAttribute("listaDiretor", null);
        } else {
            session.setAttribute("filtroDiretor", vo);
//            session.setAttribute("listaDiretor", diretorController.filtrarLike(vo));
        }

        response.sendRedirect("/views/diretor/lista.jsp");
    }
}