package br.com.sonner.estagio.servlet.sala;

import br.com.sonner.estagio.controller.SalaControllerImpl;
import br.com.sonner.estagio.model.Sala;
import br.com.sonner.estagio.vos.SalaFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/sala/preenche-vo")

public class PreencheVo extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        SalaControllerImpl salaController = new SalaControllerImpl();
        SalaFiltroVO vo = new SalaFiltroVO();
        Sala sala = salaController.getOne(Long.valueOf(request.getParameter("id")));

        vo.setId(sala.getId());
        vo.setNome(sala.getNome());
        vo.setEscola(sala.getEscola().getId());


        HttpSession session = request.getSession();
        session.setAttribute("salaParaEditar", vo);

        response.sendRedirect("/views/sala/atualiza.jsp");
    }
}
