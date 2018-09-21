package br.com.sonner.estagio.servlet.funcionario;

import br.com.sonner.estagio.controller.FuncionarioControllerImpl;
import br.com.sonner.estagio.model.Funcionario;
import br.com.sonner.estagio.vos.FuncionarioFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/funcionario/preenche-vo")
public class PreencheVo extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        FuncionarioControllerImpl funcionarioController = new FuncionarioControllerImpl();
        FuncionarioFiltroVO vo = new FuncionarioFiltroVO();

        Funcionario funcionario = funcionarioController.getOne(Long.valueOf(request.getParameter("id")));
        vo.setId(funcionario.getId());
        vo.setPessoa(funcionario.getPessoa());
        try {
            vo.setAdmissao(new SimpleDateFormat("dd/MM/yyyy").parse(funcionario.getAdmissao()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession();
        session.setAttribute("funcionario-para-editar", vo);

        response.sendRedirect("/views/funcionario/atualiza.jsp");
    }
}
