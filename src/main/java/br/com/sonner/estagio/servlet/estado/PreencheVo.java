package br.com.sonner.estagio.servlet.estado;

import br.com.sonner.estagio.controller.EstadoControllerImpl;
import br.com.sonner.estagio.model.Estado;
import br.com.sonner.estagio.vos.EstadoFiltroVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/preenche-vo")
public class PreencheVo extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EstadoControllerImpl estadoController = new EstadoControllerImpl();
        EstadoFiltroVO vo = new EstadoFiltroVO();

        Estado estado = estadoController.getOne(Long.valueOf(request.getParameter("id")));
        vo.setId(estado.getId());
        vo.setEstado(estado.getNome());
        vo.setAbv(estado.getAbv());


        HttpSession session = request.getSession();
        session.setAttribute("estado-para-editar", vo);

        response.sendRedirect("/views/estado/atualiza.jsp");
    }
}
