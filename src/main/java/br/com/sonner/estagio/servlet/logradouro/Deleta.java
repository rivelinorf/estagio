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

        HttpSession session = request.getSession();
        EnderecoControllerImpl enderecoController = new EnderecoControllerImpl();

        EnderecoFiltroVO enderecoFiltroVO = new EnderecoFiltroVO();

        enderecoFiltroVO.setLogradouro(Long.valueOf(request.getParameter("id")));
        enderecoFiltroVO.setBairro(null);
        enderecoFiltroVO.setCep("");
        enderecoFiltroVO.setComplemento("");
        enderecoFiltroVO.setNumero(null);

        if (enderecoController.filtrar(enderecoFiltroVO).size() > 0) {
            session.setAttribute("errors", "Impossivel deletar!, Logradouro possui relacionamento");
        } else {
            logradouroController.delete(Long.valueOf(request.getParameter("id")));
            session.setAttribute("success", "Logradouro deletado com sucesso");
        }


        session.setAttribute("listaLogradouro", logradouroController.filtrar((LogradouroFiltroVO) session.getAttribute("filtroLogradouro")));

        response.sendRedirect("/views/logradouro/lista.jsp");
    }
}
