package br.com.sonner.estagio.servlet.logradouro;

import br.com.sonner.estagio.controller.EnderecoControllerImpl;
import br.com.sonner.estagio.controller.LogradouroControllerImpl;
import br.com.sonner.estagio.vos.EnderecoFiltroVO;
import br.com.sonner.estagio.vos.LogradouroFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/deleta-logradouro")
public class Deleta extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LogradouroControllerImpl logradouroController = new LogradouroControllerImpl();
        LogradouroFiltroVO vo = new LogradouroFiltroVO();
        HttpSession session = request.getSession();
        EnderecoControllerImpl enderecoController = new EnderecoControllerImpl();
        EnderecoFiltroVO enderecoFiltroVO = new EnderecoFiltroVO();
        enderecoFiltroVO.setLogradouro(Long.valueOf(request.getParameter("id")));


        if (enderecoController.filtrar(enderecoFiltroVO).size() == 0) {
            logradouroController.delete(Long.valueOf(request.getParameter("id")));
            session.setAttribute("success", "Logradouro deletado com sucesso");
        } else {
            session.setAttribute("errors", "Impossivel deletar!, Logradouro possui relacionamento");

        }

        vo.setNome("");
        vo.setCidade(null);
        vo.setTipologradouro(null);

        session.setAttribute("listaLogradouro", logradouroController.filtrar(vo));

        response.sendRedirect("/views/logradouro/lista.jsp");
    }
}
