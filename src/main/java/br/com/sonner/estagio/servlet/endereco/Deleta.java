package br.com.sonner.estagio.servlet.endereco;

import br.com.sonner.estagio.controller.EnderecoControllerImpl;
import br.com.sonner.estagio.vos.EnderecoFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/endereco-deleta")
public class Deleta extends HttpServlet {


    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        EnderecoControllerImpl enderecoController = new EnderecoControllerImpl();

        HttpSession session = req.getSession();
        EnderecoFiltroVO voSession = (EnderecoFiltroVO) session.getAttribute("filtroEndereco");

        enderecoController.delete(Long.valueOf(req.getParameter("id")));
        session.setAttribute("success", "Endereço deletado com sucesso");

        if (voSession == null){
            voSession = new EnderecoFiltroVO();
        }

        session.setAttribute("listaEndereco", enderecoController.filtrar(voSession));

        res.sendRedirect("/views/endereco/lista.jsp");
    }

}