package br.com.sonner.estagio.servlet.diretor;

import br.com.sonner.estagio.controller.DiretorControllerImpl;
import br.com.sonner.estagio.model.Diretor;
import br.com.sonner.estagio.vos.DiretorFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/diretor/preenche-vo")
public class PreencheVo extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        DiretorControllerImpl diretorController = new DiretorControllerImpl();
        DiretorFiltroVO vo = new DiretorFiltroVO();

        Diretor diretor = diretorController.getOne(Long.valueOf(request.getParameter("id")));
        vo.setId(diretor.getId());
        vo.setFuncionario(diretor.getFuncionario());
        vo.getFuncionario().setEscola(diretor.getFuncionario().getEscola());

        try {
            vo.getFuncionario().setAdmissao(new SimpleDateFormat("dd/MM/yyyy").parse(diretor.getFuncionario().getAdmissao()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession();
        session.setAttribute("diretorParaEditar", vo);

        response.sendRedirect("/views/diretor/atualiza.jsp");
    }
}