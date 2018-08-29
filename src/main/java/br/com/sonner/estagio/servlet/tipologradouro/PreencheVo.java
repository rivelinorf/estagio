package br.com.sonner.estagio.servlet.tipologradouro;

import br.com.sonner.estagio.controller.TipoLogradouroControllerImpl;
import br.com.sonner.estagio.model.TipoLogradouro;
import br.com.sonner.estagio.vos.TipologradouroFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/tipologradouro/preenche-vo")
public class PreencheVo extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        TipoLogradouroControllerImpl tipologradouroController = new TipoLogradouroControllerImpl();
        TipologradouroFiltroVO vo = new TipologradouroFiltroVO();
        TipoLogradouro tipologradouro = tipologradouroController.getOne(Long.valueOf(request.getParameter("id")));

        vo.setId(tipologradouro.getId());
        vo.setNome(tipologradouro.getNome());

        HttpSession session = request.getSession();
        session.setAttribute("tipologradouro-para-editar", vo);

        response.sendRedirect("/views/tipologradouro/atualiza.jsp");
    }
}
