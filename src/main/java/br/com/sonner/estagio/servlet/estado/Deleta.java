package br.com.sonner.estagio.servlet.estado;

import br.com.sonner.estagio.controller.CidadeControllerImpl;
import br.com.sonner.estagio.controller.EstadoControllerImpl;
import br.com.sonner.estagio.vos.CidadeFiltroVO;
import br.com.sonner.estagio.vos.EstadoFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/estado-deleta")
public class Deleta extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        EstadoControllerImpl estadoController = new EstadoControllerImpl();
        CidadeControllerImpl cidadeController = new CidadeControllerImpl();
        CidadeFiltroVO cidadeFiltroVO = new CidadeFiltroVO();
        HttpSession session = request.getSession();
        EstadoFiltroVO vo = new EstadoFiltroVO();

        cidadeFiltroVO.setNome("");
        cidadeFiltroVO.setCep("");
        cidadeFiltroVO.setCod("");
        cidadeFiltroVO.setEstado(Long.valueOf(request.getParameter("id")));

        if (cidadeController.filtrar(cidadeFiltroVO).size() > 0) {
            session.setAttribute("errors", "Impossivel deletar!, Estado possue relacionamento");
        } else {
            estadoController.delete(Long.valueOf(request.getParameter("id")));
            session.setAttribute("errors", "");
            session.setAttribute("success", "Estado deletado com sucesso");
        }

        vo.setEstado("");
        vo.setAbv("");
        session.setAttribute("listaEstado", estadoController.filtrar(vo));
        response.sendRedirect("/views/estado/lista.jsp");
    }
}
