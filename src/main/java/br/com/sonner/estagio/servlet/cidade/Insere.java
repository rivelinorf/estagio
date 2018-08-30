package br.com.sonner.estagio.servlet.cidade;

import br.com.sonner.estagio.controller.CidadeControllerImpl;
import br.com.sonner.estagio.controller.EstadoControllerImpl;
import br.com.sonner.estagio.controller.api.EstadoController;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.model.Estado;
import br.com.sonner.estagio.vos.CidadeFiltroVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/insere-cidade")
public class Insere extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        CidadeControllerImpl cidadeController = new CidadeControllerImpl();
        EstadoController estadoController = new EstadoControllerImpl();
        CidadeFiltroVO vo = new CidadeFiltroVO();
        HttpSession session = req.getSession();
        Cidade novaCidade = new Cidade();
        String nome = "";
        String codigo = "";
        String cep = "";
        Estado estado = null;

        if (req.getParameter("estado") != "" && req.getParameter("estado") != null) {
            estado = estadoController.getOne(Long.valueOf(req.getParameter("estado")));
        }

        if (req.getParameter("nome") != "" && req.getParameter("nome") != null) {
            nome = req.getParameter("nome");
        }

        if (req.getParameter("codigo") != "" && req.getParameter("codigo") != null) {
            codigo = req.getParameter("codigo");
        }

        if (req.getParameter("cep") != "" && req.getParameter("cep") != null) {
            cep = req.getParameter("cep");
        }

        novaCidade.setCep(cep);
        novaCidade.setCod(codigo);
        novaCidade.setEstado(estado);
        novaCidade.setNome(nome);

        List<String> erros = cidadeController.validation(novaCidade);

        if (erros.size() == 0) {

            vo.setNome("");
            vo.setCod("");
            vo.setCep("");
            vo.setEstado(null);
            vo.setId(null);

            vo.setNome(novaCidade.getNome());
            vo.setEstado(novaCidade.getEstado().getId());

            List<Cidade> verificacidade = cidadeController.filtrar(vo);

            vo.setNome("");
            vo.setEstado(null);

            vo.setCep(novaCidade.getCep());

            List<Cidade> verificacep = cidadeController.filtrar(vo);

            if (verificacidade.size() == 0 && verificacep.size() == 0) {

                cidadeController.save(novaCidade);

                vo.setNome("");
                vo.setCod("");
                vo.setCep("");
                vo.setEstado(null);
                vo.setId(null);

                session.setAttribute("listaCidade", cidadeController.filtrar(vo));
                session.setAttribute("success", "Cidade inserida com sucesso");

                res.sendRedirect("/views/cidade/lista.jsp");
            } else {

                String existe = "";
                if (verificacidade.size() > 0) {

                    existe = "Cidade já cadastrada!";
                } else {
                    existe = "CEP já utilizado";
                }
                session.setAttribute("errors", existe);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/cidade/insere.jsp");
                requestDispatcher.forward(req, res);
            }

        } else {
            session.setAttribute("errors", erros);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/cidade/insere.jsp");
            requestDispatcher.forward(req, res);
        }

    }
}
