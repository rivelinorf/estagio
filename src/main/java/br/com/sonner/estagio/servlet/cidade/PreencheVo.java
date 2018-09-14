package br.com.sonner.estagio.servlet.cidade;

import br.com.sonner.estagio.controller.CidadeControllerImpl;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.vos.CidadeFiltroVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/cidade/preenche-vo")
public class PreencheVo extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CidadeControllerImpl cidadeController = new CidadeControllerImpl();
        CidadeFiltroVO vo = new CidadeFiltroVO();

        Cidade cidade = cidadeController.getOne(Long.valueOf(request.getParameter("id")));
        vo.setId(cidade.getId());
        vo.setNome(cidade.getNome());
        vo.setCep(cidade.getCep());
        vo.setCod(cidade.getCod());
        vo.setEstado(cidade.getEstado().getId());


        HttpSession session = request.getSession();
        session.setAttribute("cidadeParaEditar", vo);

        response.sendRedirect("/views/cidade/atualiza.jsp");
    }
}
