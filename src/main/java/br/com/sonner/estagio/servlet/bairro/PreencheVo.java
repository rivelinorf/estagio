package br.com.sonner.estagio.servlet.bairro;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sonner.estagio.controller.BairroControllerImpl;
import br.com.sonner.estagio.controller.CidadeControllerImpl;
import br.com.sonner.estagio.controller.EstadoControllerImpl;
import br.com.sonner.estagio.model.Bairro;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.model.Estado;
import br.com.sonner.estagio.vos.BairroFiltroVO;
import br.com.sonner.estagio.vos.CidadeFiltroVO;

@WebServlet("/bairro/preenche-vo")
public class PreencheVo extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        BairroControllerImpl bairroController = new BairroControllerImpl();
        BairroFiltroVO vo = new BairroFiltroVO();
        EstadoControllerImpl estadoController = new EstadoControllerImpl();
        CidadeControllerImpl cidadeController = new CidadeControllerImpl();
        CidadeFiltroVO cidadevo = new CidadeFiltroVO();

        HttpSession session = request.getSession();

        Bairro bairro = new Bairro();

        if (request.getParameter("id") != "" && request.getParameter("id") != null) {
            bairro = bairroController.getOne(Long.valueOf(request.getParameter("id")));
            vo.setId(bairro.getId());
            vo.setNome(bairro.getNome());
            vo.setCidade(bairro.getCidade().getId());
        } else {
            vo = (BairroFiltroVO) session.getAttribute("bairroParaEditar");
            bairro.setId(vo.getId());
            bairro.setCidade(cidadeController.getOne(vo.getCidade()));
            bairro.setNome(vo.getNome());
        }
        Cidade cidade = cidadeController.getOne(bairro.getCidade().getId());
        Estado estado = estadoController.getOne(bairro.getCidade().getEstado().getId());

        cidadevo.setId(cidade.getId());
        cidadevo.setNome(cidade.getNome());
        cidadevo.setCep(cidade.getCep());
        cidadevo.setCod(cidade.getCod());
        cidadevo.setEstado(cidade.getEstado().getId());

        session.setAttribute("listaCidade_atualiza", cidadeController.filtrar(cidadevo));
        session.setAttribute("bairroParaEditar", vo);
        session.setAttribute("filtroCidade_atualiza", cidadevo);
        session.setAttribute("estado", estado);

        response.sendRedirect("/views/bairro/atualiza.jsp");
    }

}
