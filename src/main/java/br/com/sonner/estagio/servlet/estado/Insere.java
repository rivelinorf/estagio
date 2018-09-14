package br.com.sonner.estagio.servlet.estado;

import br.com.sonner.estagio.controller.EstadoControllerImpl;
import br.com.sonner.estagio.model.Estado;
import br.com.sonner.estagio.vos.EstadoFiltroVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/insere-estado")
public class Insere extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Estado aux = new Estado();
        EstadoControllerImpl estadoController = new EstadoControllerImpl();
        HttpSession session = request.getSession();
        EstadoFiltroVO vo = new EstadoFiltroVO();
        aux.setNome("");
        aux.setAbv("");

        if (request.getParameter("nome") != null && request.getParameter("nome") != "") {
            aux.setNome(request.getParameter("nome"));
        }
        if (request.getParameter("abv") != "" && request.getParameter("abv") != null) {
            aux.setAbv(request.getParameter("abv"));
        }

        List<String> erros = estadoController.validation(aux);

        session.setAttribute("filtroEstado", null);

        if (erros.size() == 0) {

            vo.setEstado("");
            vo.setAbv("");
            vo.setId(null);

            vo.setEstado(aux.getNome());

            List<Estado> verificaestado = estadoController.filtrar(vo);

            vo.setEstado(null);
            vo.setAbv(aux.getAbv());

            List<Estado> verificaabv = estadoController.filtrar(vo);

            if (verificaestado.size() == 0 && verificaabv.size() == 0) {

                estadoController.save(aux);

                vo.setEstado("");
                vo.setAbv("");
                vo.setId(null);

                session.setAttribute("listaEstado", estadoController.filtrar(vo));
                session.setAttribute("success", "Estado inserido com sucesso");
                response.sendRedirect("/views/estado/lista.jsp");
            } else {

                String existe ;

                if (verificaestado.size() > 0) {
                    existe = "Estado já cadastrado!";

                } else {
                    existe = "Abreviação já cadastrada!";

                }
                session.setAttribute("errors", existe);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/estado/insere.jsp");
                requestDispatcher.forward(request, response);
            }

        } else {
            session.setAttribute("errors", erros);
            session.setAttribute("campoEstado", aux);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/estado/insere.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
