package br.com.sonner.estagio.servlet.aluno;

import br.com.sonner.estagio.controller.AlunoControllerImpl;
import br.com.sonner.estagio.controller.EnderecoControllerImpl;
import br.com.sonner.estagio.controller.PessoaControllerImpl;
import br.com.sonner.estagio.controller.TipoLogradouroControllerImpl;
import br.com.sonner.estagio.controller.api.PessoaController;
import br.com.sonner.estagio.model.Aluno;
import br.com.sonner.estagio.model.Pessoa;
import br.com.sonner.estagio.model.SexoEnum;
import br.com.sonner.estagio.vos.AlunoFiltroVO;
import br.com.sonner.estagio.vos.TipologradouroFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/pesquisa-aluno")
public class Pesquisa extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        AlunoControllerImpl alunoController = new AlunoControllerImpl();

        Pessoa pessoa = new Pessoa();
        AlunoFiltroVO vo = new AlunoFiltroVO();
        SexoEnum sexo;

        pessoa.setNome(request.getParameter("nome"));

        if (request.getParameter("sexo") != null) {
            sexo = (request.getParameter("sexo").equals("m")) ? SexoEnum.MASCULINO : SexoEnum.FEMININO;
            pessoa.setSexo(sexo);
        }

        vo.setPessoa(pessoa);

        HttpSession session = request.getSession();

        if (vo.getPessoa() == null) {
            session.setAttribute("filtroAluno", null);
            session.setAttribute("listaAluno", null);
        } else {

            session.setAttribute("filtroAluno", vo);
            session.setAttribute("listaAluno", alunoController.filtrarLike(vo));
        }

        response.sendRedirect("/views/aluno/lista.jsp");
    }
}
