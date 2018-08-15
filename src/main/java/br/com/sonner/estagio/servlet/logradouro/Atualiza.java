package br.com.sonner.estagio.servlet.logradouro;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sonner.estagio.controller.BairroControllerImpl;
import br.com.sonner.estagio.controller.CidadeControllerImpl;
import br.com.sonner.estagio.controller.LogradouroControllerImpl;
import br.com.sonner.estagio.controller.TipoLogradouroControllerImpl;
import br.com.sonner.estagio.controller.api.CidadeController;
import br.com.sonner.estagio.controller.api.LogradouroController;
import br.com.sonner.estagio.controller.api.TipoLogradouroController;
import br.com.sonner.estagio.model.Bairro;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.model.Logradouro;
import br.com.sonner.estagio.model.TipoLogradouro;
import br.com.sonner.estagio.vos.LogradouroFiltroVO;

@WebServlet("/atualiza-logradouro")
public class Atualiza extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LogradouroControllerImpl logradouroController = new LogradouroControllerImpl();
        LogradouroFiltroVO vo = new LogradouroFiltroVO();
        CidadeController cidadeController = new CidadeControllerImpl();
        TipoLogradouroController tipoLogradouroController = new TipoLogradouroControllerImpl();
        Logradouro novoLogradouro = new Logradouro();
        HttpSession session = request.getSession();

        novoLogradouro.setId((Long.valueOf(request.getParameter("id"))));
        novoLogradouro.setNome(request.getParameter("logradouro"));
        novoLogradouro.setCidade(cidadeController.getOne(Long.valueOf(request.getParameter("cidade"))));
        novoLogradouro.setTipologradouro(tipoLogradouroController.getOne(Long.valueOf(request.getParameter("tipologradouro"))));

        List<String> erros = logradouroController.validation(novoLogradouro);

        if (erros.size() == 0) {
            logradouroController.update(novoLogradouro);
            vo.setNome("");
            vo.setCidade(null);
            vo.setTipologradouro(null);

            session.setAttribute("listaLogradouro", logradouroController.filtrar(vo));
            session.setAttribute("success", "Logradouro atualizada com sucesso");


            response.sendRedirect("/views/logradouro/lista.jsp");

        } else {
            session.setAttribute("errors", erros);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/logradouro/atualiza.jsp");
            requestDispatcher.forward(request, response);
        }


    }
}
