package br.com.sonner.estagio.servlet.bairro;

import br.com.sonner.estagio.controller.BairroControllerImpl;
import br.com.sonner.estagio.controller.EnderecoControllerImpl;
import br.com.sonner.estagio.vos.BairroFiltroVO;
import br.com.sonner.estagio.vos.EnderecoFiltroVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/bairro-deleta")
public class Deleta extends HttpServlet {


    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        BairroControllerImpl bairroController = new BairroControllerImpl();
        EnderecoControllerImpl enderecoController = new EnderecoControllerImpl();
        EnderecoFiltroVO enderecoVO = new EnderecoFiltroVO();
        BairroFiltroVO vo = new BairroFiltroVO();
        HttpSession session = req.getSession();

        enderecoVO.setBairro(Long.valueOf(req.getParameter("id")));
        enderecoVO.setCep("");
        enderecoVO.setComplemento("");
        enderecoVO.setLogradouro(null);
        enderecoVO.setNumero(null);

        if (enderecoController.filtrar(enderecoVO).size() > 0) {
            session.setAttribute("errors", "Impossível deletar! Bairro possui relacionamentos");
        } else {
            bairroController.delete(Long.valueOf(req.getParameter("id")));
            session.setAttribute("success", "Bairro deletado com sucesso");
        }


        vo.setCidade(null);
        vo.setNome("");

        session.setAttribute("listaBairro", bairroController.filtrar(vo));

        res.sendRedirect("/views/bairro/lista.jsp");
    }

}
