package br.com.sonner.estagio.servlet.sala;

import br.com.sonner.estagio.controller.SalaControllerImpl;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.EnderecoFiltroVO;
import br.com.sonner.estagio.vos.EscolaFiltroVO;
import br.com.sonner.estagio.vos.SalaFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/deleta-sala")
public class Deleta extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SalaControllerImpl salaController = new SalaControllerImpl();

        HttpSession session = request.getSession();
        SalaFiltroVO salaFiltroVO = (SalaFiltroVO) session.getAttribute("filtroSala");

        EscolaFiltroVO escolaFiltroVO = new EscolaFiltroVO();

        EnderecoFiltroVO enderecoFiltroVO = new EnderecoFiltroVO();
        escolaFiltroVO.setEndereco(null);
        escolaFiltroVO.setNome("");
        //   escolaFiltroVO.setSala(Long.valueOf(request.getParameter("id")));


        try {
            salaController.delete(Long.valueOf(request.getParameter("id")));

        } catch (CustomException e) {
            session.setAttribute("errors", e.getMessage());
        }

        if (salaFiltroVO == null) {
            salaFiltroVO = new SalaFiltroVO();
        }

        session.setAttribute("listaSala", salaController.filtrar(salaFiltroVO));
        response.sendRedirect("/views/sala/lista.jsp");
    }
}
