package br.com.sonner.estagio.servlet.cidade;

import br.com.sonner.estagio.controller.CidadeControllerImpl;
import br.com.sonner.estagio.vos.CidadeFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/pesquisa-cidade")
public class Pesquisa extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws  IOException {
        CidadeControllerImpl cidadeController = new CidadeControllerImpl();
        CidadeFiltroVO vo = new CidadeFiltroVO();

        vo.setNome(request.getParameter("cidade"));
        vo.setCep(request.getParameter("cep"));
        vo.setCod(request.getParameter("codigo"));

        if (request.getParameter("estado") != "" && request.getParameter("estado") != null) {
            vo.setEstado(Long.valueOf(request.getParameter("estado")));
        }

        HttpSession session = request.getSession();

        if (vo.getNome() == null && vo.getCep() == null && vo.getCod() == null && vo.getEstado() == null) {
            session.setAttribute("filtroCidade", null);
            session.setAttribute("listaCidade", null);
        } else {
            session.setAttribute("filtroCidade", vo);
            session.setAttribute("listaCidade", cidadeController.filtrarLike(vo));
        }

        response.sendRedirect("/views/cidade/lista.jsp");
    }
}
