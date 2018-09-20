package br.com.sonner.estagio.servlet.sala;

import br.com.sonner.estagio.controller.EscolaControllerImpl;
import br.com.sonner.estagio.controller.SalaControllerImpl;
import br.com.sonner.estagio.model.Escola;
import br.com.sonner.estagio.model.Sala;
import br.com.sonner.estagio.vos.SalaFiltroVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/insere-sala")
public class Insere extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        SalaControllerImpl salaController = new SalaControllerImpl();
        EscolaControllerImpl escolaController = new EscolaControllerImpl();

        SalaFiltroVO vo = new SalaFiltroVO();

        HttpSession session = req.getSession();

        Sala aux = new Sala();
        aux.setNome("");
        aux.setEscola(null);


        Escola escola = null;
        String sala = req.getParameter("sala");

        if (req.getParameter("escola") != null && !req.getParameter("escola").isEmpty() && !req.getParameter("escola").equals("-1")) {
            escola = escolaController.getOne(Long.valueOf(req.getParameter("escola")));
        }

        Sala novasala = new Sala();
        novasala.setNome(sala);
        novasala.setEscola(escola);

        List<String> erros = salaController.validation(novasala);

        if (erros.size() == 0) {
            vo.setEscola(novasala.getEscola().getId());
            vo.setNome(novasala.getNome());

            List<Sala> verifica = salaController.filtrar(vo);

            if (verifica.size() == 0) {

                salaController.save(novasala);
                vo.setNome("");
                vo.setEscola(null);

                session.setAttribute("listaSala", (salaController.filtrar(vo)));
                session.setAttribute("success", "Sala inserida com sucesso");

                res.sendRedirect("/views/sala/lista.jsp");
            } else {
                String existe = "Sala já cadastrada!";

                session.setAttribute("errors", existe);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/sala/insere.jsp");
                requestDispatcher.forward(req, res);
            }
        } else {
            session.setAttribute("errors", erros);
            session.setAttribute("campoSala", aux);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/sala/insere.jsp");
            requestDispatcher.forward(req, res);
        }
    }
}
