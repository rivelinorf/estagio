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

@WebServlet("/atualiza-logradouro")
public class Atualiza extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        LogradouroControllerImpl logradouroController = new LogradouroControllerImpl();
        LogradouroFiltroVO vo = new LogradouroFiltroVO();
        CidadeControllerImpl cidadeController = new CidadeControllerImpl();
        TipoLogradouroController tipoLogradouroController = new TipoLogradouroControllerImpl();
        HttpSession session = request.getSession();

        Cidade cidade = null;
        TipoLogradouro tipoLogradouro = null;
        String logradouro = request.getParameter("logradouro");

        if (request.getParameter("cidade") != null && !request.getParameter("cidade").isEmpty()
                && !request.getParameter("cidade").equals("-1")) {
            cidade = cidadeController.getOne(Long.valueOf(request.getParameter("cidade")));
        }

        if (request.getParameter("tipologradouro") != null && !request.getParameter("tipologradouro").isEmpty()) {
            tipoLogradouro = tipoLogradouroController.getOne(Long.valueOf(request.getParameter("tipologradouro")));
        }

        Logradouro novoLogradouro = new Logradouro();
        novoLogradouro.setId((Long.valueOf(request.getParameter("id"))));
        novoLogradouro.setNome(logradouro);
        novoLogradouro.setTipologradouro(tipoLogradouro);
        novoLogradouro.setCidade(cidade);

        List<String> erros = logradouroController.validation(novoLogradouro);

        if (erros.size() == 0) {

            vo.setCidade(novoLogradouro.getCidade().getId());
            vo.setNome(novoLogradouro.getNome());
            vo.setTipologradouro(novoLogradouro.getTipologradouro().getId());

            List<Logradouro> verifica = logradouroController.filtrar(vo);

            if (verifica.size() == 0) {
                logradouroController.update(novoLogradouro);

                vo.setNome("");
                vo.setCidade(null);
                vo.setTipologradouro(null);

                session.setAttribute("listaLogradouro", logradouroController.filtrar(vo));
                session.setAttribute("success", "Logradouro atualizada com sucesso");
                response.sendRedirect("/views/logradouro/lista.jsp");
            } else {
                LogradouroFiltroVO logradouroantigo = (LogradouroFiltroVO) session.getAttribute("logradouroParaEditar");

                if (vo.getCidade().equals(logradouroantigo.getCidade())
                        && vo.getNome().equals(logradouroantigo.getNome())) {
                    vo.setNome("");
                    vo.setCidade(null);
                    vo.setTipologradouro(null);

                    session.setAttribute("listaLogradouro", logradouroController.filtrar(vo));
                }

                String existe = "Logradouro já cadastrado!";

                session.setAttribute("errors", existe);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/logradouro/atualiza.jsp");
                requestDispatcher.forward(request, response);
            }

        } else {
            session.setAttribute("errors", erros);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/logradouro/atualiza.jsp");
            requestDispatcher.forward(request, response);
        }

    }
}
