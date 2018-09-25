package br.com.sonner.estagio.servlet.diretor;

import br.com.sonner.estagio.controller.DiretorControllerImpl;
import br.com.sonner.estagio.controller.EscolaControllerImpl;
import br.com.sonner.estagio.model.Diretor;
import br.com.sonner.estagio.model.Escola;
import br.com.sonner.estagio.model.Funcionario;
import br.com.sonner.estagio.model.Pessoa;
import br.com.sonner.estagio.vos.DiretorFiltroVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/atualiza-diretor")
public class Atualiza extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DiretorControllerImpl diretorController = new DiretorControllerImpl();
        EscolaControllerImpl escolaController = new EscolaControllerImpl();
        HttpSession session = request.getSession();
        Diretor diretor = new Diretor();
        Pessoa pessoa = new Pessoa();
        Funcionario funcionario = new Funcionario();
        DiretorFiltroVO vo = new DiretorFiltroVO(), voSession = (DiretorFiltroVO) session.getAttribute("diretorParaEditar");

        if (voSession.getFuncionario().getPessoa().getNome().equals(request.getParameter("nome"))) {
            response.sendRedirect("/views/diretor/lista.jsp");
            return;
        }

        diretor = diretorController.getOne(Long.valueOf(request.getParameter("id")));

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date dataNascimento = formatter.parse(request.getParameter("data-nascimento"));
            Date dataAdmissao = formatter.parse(request.getParameter("data-admissao"));
            diretor.getFuncionario().setAdmissao(dataAdmissao);
            diretor.getFuncionario().getPessoa().setDataNascimento(dataNascimento);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        diretor.getFuncionario().getPessoa().setNome(request.getParameter("nome"));
        diretor.getFuncionario().getPessoa().setPai(request.getParameter("pai"));
        diretor.getFuncionario().getPessoa().setMae(request.getParameter("mae"));
        diretor.getFuncionario().getPessoa().setCpf(request.getParameter("cpf"));
        diretor.getFuncionario().setEscola(escolaController.getOne(Long.valueOf(request.getParameter("escola"))));

        diretorController.update(diretor);

        vo.setFuncionario(new Funcionario());
        vo.getFuncionario().setPessoa(new Pessoa());
        vo.getFuncionario().setEscola(new Escola());

        session.setAttribute("listaDiretor", diretorController.filtrar(vo));
        session.setAttribute("success", "Diretor atualizado com sucesso");

        response.sendRedirect("/views/diretor/lista.jsp");
    }
}
