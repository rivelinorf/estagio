package br.com.sonner.estagio.servlet.cidade;

import br.com.sonner.estagio.controller.BairroControllerImpl;
import br.com.sonner.estagio.controller.CidadeControllerImpl;
import br.com.sonner.estagio.controller.LogradouroControllerImpl;
import br.com.sonner.estagio.vos.BairroFiltroVO;
import br.com.sonner.estagio.vos.CidadeFiltroVO;
import br.com.sonner.estagio.vos.LogradouroFiltroVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/cidade-deleta")
public class Deleta extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CidadeControllerImpl cidadeController = new CidadeControllerImpl();
        CidadeFiltroVO vo = new CidadeFiltroVO();
        HttpSession session = request.getSession();

        BairroControllerImpl bairroController = new BairroControllerImpl();
        LogradouroControllerImpl logradouroController = new LogradouroControllerImpl();

        BairroFiltroVO bairroFiltroVO = new BairroFiltroVO();
        bairroFiltroVO.setCidade(Long.valueOf(request.getParameter("id")));
        LogradouroFiltroVO logradouroFiltroVO = new LogradouroFiltroVO();
        logradouroFiltroVO.setCidade(Long.valueOf(request.getParameter("id")));

        if (bairroController.filtrar(bairroFiltroVO).size() == 0 && logradouroController.filtrar(logradouroFiltroVO).size() == 0) {
            cidadeController.delete(Long.valueOf(request.getParameter("id")));
            session.setAttribute("success", "Cidade deletada com sucesso");
        } else {
            session.setAttribute("errors", "Impossivel deletar!, Cidade possui relacionamento");
        }

        vo.setNome("");
        vo.setCod("");
        vo.setCep("");
        vo.setEstado(null);

        session.setAttribute("listaCidade", cidadeController.filtrar(vo));

        response.sendRedirect("/views/cidade/lista.jsp");


    }
}
