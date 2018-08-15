
package br.com.sonner.estagio.servlet.logradouro;

import br.com.sonner.estagio.controller.CidadeControllerImpl;
import br.com.sonner.estagio.controller.LogradouroControllerImpl;
import br.com.sonner.estagio.controller.TipoLogradouroControllerImpl;
import br.com.sonner.estagio.controller.api.CidadeController;
import br.com.sonner.estagio.controller.api.LogradouroController;
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
        LogradouroController logradouroController = new LogradouroControllerImpl();
        TipoLogradouroController tipoLogradouroController = new TipoLogradouroControllerImpl();
        CidadeController cidadeController = new CidadeControllerImpl();
        LogradouroFiltroVO vo = new LogradouroFiltroVO();
        HttpSession session = req.getSession();

        Cidade cidade = cidadeController.getOne(Long.valueOf(req.getParameter("cidade")));
        TipoLogradouro tipoLogradouro = tipoLogradouroController.getOne(Long.valueOf(req.getParameter("tipologradouro")));

        Logradouro novologradouro = new Logradouro(req.getParameter("nome"), tipoLogradouro, cidade);

        List<String> erros = logradouroController.validation(novologradouro);

        if (erros.size() == 0) {
            logradouroController.save(novologradouro);
            vo.setNome("");
            vo.setTipologradouro(null);
            vo.setCidade(null);
            session.setAttribute("listaLogradouro", ((LogradouroControllerImpl) logradouroController).filtrar(vo));
            session.setAttribute("success", "Logradouro inserido com sucesso");

            res.sendRedirect("/views/logradouro/lista.jsp");
        } else {
            session.setAttribute("errors", erros);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/logradouro/insere.jsp");
            requestDispatcher.forward(req, res);
        }
    }

}
