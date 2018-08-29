package br.com.sonner.estagio.servlet.endereco;

import br.com.sonner.estagio.controller.EnderecoControllerImpl;
import br.com.sonner.estagio.vos.EnderecoFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/pesquisa-endereco")
public class Pesquisa extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        EnderecoControllerImpl enderecoController = new EnderecoControllerImpl();
        EnderecoFiltroVO vo = new EnderecoFiltroVO();

        vo.setCep(request.getParameter("cep"));
        vo.setComplemento(request.getParameter("complemento"));

        if (request.getParameter("bairro") != "" && request.getParameter("bairro") != null) {
            vo.setBairro(Long.valueOf(request.getParameter("bairro")));
        }

        if (request.getParameter("logradouro") != "" && request.getParameter("logradouro") != null) {
            vo.setLogradouro(Long.valueOf(request.getParameter("logradouro")));
        }

        if (request.getParameter("numero") != "" && request.getParameter("numero") != null) {
            vo.setNumero(Integer.parseInt(request.getParameter("numero")));
        }

        HttpSession session = request.getSession();

        if (vo.getCep() == null && vo.getComplemento() == null && vo.getBairro() == null && vo.getLogradouro() == null
                && vo.getNumero() == null) {
            session.setAttribute("filtroEndereco", null);
            session.setAttribute("listaEndereco", null);
        } else {
            session.setAttribute("filtroEndereco", vo);
            session.setAttribute("listaEndereco", enderecoController.filtrar(vo));
        }

        response.sendRedirect("/views/endereco/lista.jsp");

    }

}
