package br.com.sonner.estagio.servlet.logradouro;

import br.com.sonner.estagio.controller.LogradouroControllerImpl;
import br.com.sonner.estagio.model.Logradouro;
import br.com.sonner.estagio.vos.LogradouroFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logradouro/preenche-vo")
public class PreencheVo extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        LogradouroControllerImpl logradouroController = new LogradouroControllerImpl();
        LogradouroFiltroVO vo = new LogradouroFiltroVO();
        Logradouro logradouro = logradouroController.getOne(Long.valueOf(request.getParameter("id")));

        vo.setId(logradouro.getId());
        vo.setNome(logradouro.getNome());
        vo.setCidade(logradouro.getCidade().getId());
        vo.setTipologradouro(logradouro.getTipologradouro().getId());


        HttpSession session = request.getSession();
        session.setAttribute("logradouroParaEditar", vo);

        response.sendRedirect("/views/logradouro/atualiza.jsp");
    }
}
