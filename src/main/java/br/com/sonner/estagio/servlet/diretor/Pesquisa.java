package br.com.sonner.estagio.servlet.diretor;

import br.com.sonner.estagio.controller.DiretorControllerImpl;
import br.com.sonner.estagio.controller.EscolaControllerImpl;
import br.com.sonner.estagio.controller.api.EscolaController;
import br.com.sonner.estagio.model.Escola;
import br.com.sonner.estagio.model.Funcionario;
import br.com.sonner.estagio.model.Pessoa;
import br.com.sonner.estagio.vos.DiretorFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/pesquisa-diretor")
public class Pesquisa extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        DiretorControllerImpl diretorController = new DiretorControllerImpl();
        EscolaControllerImpl escolaController = new EscolaControllerImpl();
        HttpSession session = request.getSession();
        DiretorFiltroVO vo = new DiretorFiltroVO();
        Pessoa pessoa = new Pessoa();
        Funcionario funcionario = new Funcionario();

        pessoa.setNome(request.getParameter("nome"));
        pessoa.setCpf(request.getParameter("cpf"));

        if (request.getParameter("escola") != null && request.getParameter("escola") != "") {
            funcionario.setEscola(escolaController.getOne(Long.valueOf(request.getParameter("escola"))));
        } else {
            funcionario.setEscola(new Escola());
        }

        funcionario.setPessoa(pessoa);
        vo.setFuncionario(funcionario);

        if (pessoa.getNome() == null && pessoa.getCpf() == null) { // primeira vez entrando na pagina
            session.setAttribute("filtroDiretor", null);
            session.setAttribute("listaDiretor", null);
        } else {
            session.setAttribute("filtroDiretor", vo);
            session.setAttribute("listaDiretor", diretorController.filtrarLike(vo));
        }

        response.sendRedirect("/views/diretor/lista.jsp");
    }
}