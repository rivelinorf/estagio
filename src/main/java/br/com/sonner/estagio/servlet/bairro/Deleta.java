package br.com.sonner.estagio.servlet.bairro;

import br.com.sonner.estagio.controller.BairroControllerImpl;
import br.com.sonner.estagio.controller.EnderecoControllerImpl;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.vos.BairroFiltroVO;
import br.com.sonner.estagio.vos.EnderecoFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/bairro-deleta")
public class Deleta extends HttpServlet {


    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        BairroControllerImpl bairroController = new BairroControllerImpl();
        EnderecoFiltroVO enderecoVO = new EnderecoFiltroVO();
        
        HttpSession session = req.getSession();
        BairroFiltroVO voSession = (BairroFiltroVO) session.getAttribute("filtroBairro");

        enderecoVO.setBairro(Long.valueOf(req.getParameter("id")));
        enderecoVO.setCep("");
        enderecoVO.setComplemento("");
        enderecoVO.setLogradouro(null);
        enderecoVO.setNumero(null);

        try {
            bairroController.delete(Long.valueOf(req.getParameter("id")));
            session.setAttribute("success", "Bairro deletado com sucesso");
        } catch (CustomException e) {
            session.setAttribute("errors", e.getMessage());
        }


        if (voSession == null) {
            voSession = new BairroFiltroVO();
        }

        session.setAttribute("listaBairro", bairroController.filtrar(voSession));

        res.sendRedirect("/views/bairro/lista.jsp");
    }

}
