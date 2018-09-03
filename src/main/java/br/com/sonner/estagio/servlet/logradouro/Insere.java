
package br.com.sonner.estagio.servlet.logradouro;

import br.com.sonner.estagio.controller.CidadeControllerImpl;
import br.com.sonner.estagio.controller.LogradouroControllerImpl;
import br.com.sonner.estagio.controller.TipoLogradouroControllerImpl;
import br.com.sonner.estagio.controller.api.TipoLogradouroController;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.model.Logradouro;
import br.com.sonner.estagio.model.TipoLogradouro;
import br.com.sonner.estagio.vos.LogradouroFiltroVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/insere-logradouro")
public class Insere extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        LogradouroControllerImpl logradouroController = new LogradouroControllerImpl();
        TipoLogradouroController tipoLogradouroController = new TipoLogradouroControllerImpl();
        CidadeControllerImpl cidadeController = new CidadeControllerImpl();
        LogradouroFiltroVO vo = new LogradouroFiltroVO();
        HttpSession session = req.getSession();
        Logradouro aux = new Logradouro();
        aux.setCidade(null);
        aux.setTipologradouro(null);
        aux.setNome("");


        Cidade cidade = null;
        TipoLogradouro tipoLogradouro = null;
        String logradouro = req.getParameter("logradouro");

        if (req.getParameter("cidade") != null && !req.getParameter("cidade").isEmpty() && !req.getParameter("cidade").equals("-1")) {
            cidade = cidadeController.getOne(Long.valueOf(req.getParameter("cidade")));
        }

        if (req.getParameter("tipologradouro") != null && !req.getParameter("tipologradouro").isEmpty()) {
            tipoLogradouro = tipoLogradouroController.getOne(Long.valueOf(req.getParameter("tipologradouro")));
        }


        Logradouro novologradouro = new Logradouro();
        novologradouro.setNome(logradouro);
        novologradouro.setTipologradouro(tipoLogradouro);
        novologradouro.setCidade(cidade);

        List<String> erros = logradouroController.validation(novologradouro);

        if (erros.size() == 0) {

            vo.setCidade(novologradouro.getCidade().getId());
            vo.setNome(novologradouro.getNome());
            vo.setTipologradouro(novologradouro.getTipologradouro().getId());

            List<Logradouro> verifica = logradouroController.filtrar(vo);

            if (verifica.size() == 0) {

                logradouroController.save(novologradouro);

                vo.setNome("");
                vo.setTipologradouro(null);
                vo.setCidade(null);

                session.setAttribute("listaLogradouro", (logradouroController.filtrar(vo)));
                session.setAttribute("success", "Logradouro inserido com sucesso");

                res.sendRedirect("/views/logradouro/lista.jsp");
            } else {
                String existe = "Logradouro já cadastrado!";

                session.setAttribute("errors", existe);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/logradouro/insere.jsp");
                requestDispatcher.forward(req, res);
            }
        } else {
            session.setAttribute("errors", erros);
            session.setAttribute("campoLogradouro", aux);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/logradouro/insere.jsp");
            requestDispatcher.forward(req, res);
        }
    }

}