package br.com.sonner.estagio.servlet.endereco;

import br.com.sonner.estagio.controller.*;
import br.com.sonner.estagio.model.*;
import br.com.sonner.estagio.vos.BairroFiltroVO;
import br.com.sonner.estagio.vos.CidadeFiltroVO;
import br.com.sonner.estagio.vos.EnderecoFiltroVO;
import br.com.sonner.estagio.vos.LogradouroFiltroVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/insere-endereco")
public class Insere extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        CidadeControllerImpl cidadeController = new CidadeControllerImpl();
        CidadeFiltroVO cidadevo = new CidadeFiltroVO();

        BairroControllerImpl bairroController = new BairroControllerImpl();
        BairroFiltroVO bairrovo = new BairroFiltroVO();

        EstadoControllerImpl estadoController = new EstadoControllerImpl();
        Estado estado = new Estado();
        Cidade cidade = new Cidade();


        HttpSession session = request.getSession();

        cidadevo.setCep("");
        cidadevo.setEstado(null);
        cidadevo.setId(null);
        cidadevo.setNome("");
        cidadevo.setCod("");

        bairrovo.setCidade(null);
        bairrovo.setId(null);
        bairrovo.setNome("");

        if (request.getParameter("estado") != "" && request.getParameter("estado") != null) {
            cidadevo.setEstado(Long.valueOf(request.getParameter("estado")));
            estado = estadoController.getOne(cidadevo.getEstado());

            session.setAttribute("filtroCidade_insereEndereco", cidadevo);
            session.setAttribute("listaCidade_insereEndereco", cidadeController.filtrar(cidadevo));
            session.setAttribute("estado", estado);

        }

        if (request.getParameter("cidade") != "" && request.getParameter("cidade") != null) {
            Long id = Long.valueOf(request.getParameter("cidade"));
            cidade = cidadeController.getOne(id);


            bairrovo.setCidade(cidade.getId());

            cidadevo.setEstado(cidade.getEstado().getId());

            session.setAttribute("filtroCidade_insereEndereco", cidadevo);
            session.setAttribute("listaCidade_insereEndereco", cidadeController.filtrar(cidadevo));

            session.setAttribute("filtroBairro_insereEndereco", bairrovo);
            session.setAttribute("listaBairro_insereEndereco", bairroController.filtrar(bairrovo));

            session.setAttribute("cidade", cidade);

        }

        if (cidadevo.getEstado() == null) {

            session.setAttribute("listaCidade_insereEndereco", null);
            session.setAttribute("filtroCidade_insereEndereco", null);
            session.setAttribute("estado", null);

            if (bairrovo.getCidade() == null) {

                session.setAttribute("listaBairro_insereEndereco", null);
                session.setAttribute("filtroBairro_insereEndereco", null);
                session.setAttribute("cidade", null);
                session.setAttribute("enderecoParaInserir", null);

            }

        }

        response.sendRedirect("/views/endereco/insere.jsp");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        LogradouroControllerImpl logradouroController = new LogradouroControllerImpl();
        BairroControllerImpl bairroController = new BairroControllerImpl();
        EnderecoControllerImpl enderecoController = new EnderecoControllerImpl();
        CidadeControllerImpl cidadeController = new CidadeControllerImpl();
        EstadoControllerImpl estadoController = new EstadoControllerImpl();
        TipoLogradouroControllerImpl tipoLogradouroController = new TipoLogradouroControllerImpl();

        HttpSession session = req.getSession();


        EnderecoFiltroVO vo = new EnderecoFiltroVO();
        BairroFiltroVO bairrovo = new BairroFiltroVO();
        CidadeFiltroVO cidadevo = new CidadeFiltroVO();
        LogradouroFiltroVO logradourovo = new LogradouroFiltroVO();


        String cep = req.getParameter("cep");
        String complemento = req.getParameter("complemento");
        Integer numero = null;
        Logradouro logradouro = null;
        Bairro bairro = null;
        TipoLogradouro tipoLogradouro = null;
        Cidade cidade = null;
        Estado estado = null;

        logradourovo.setNome("");
        vo.setId(null);
        vo.setNumero(null);
        vo.setCep("");
        vo.setComplemento("");
        vo.setBairro(null);
        vo.setLogradouro(null);


        if (req.getParameter("estadoSession") != "" && req.getParameter("estadoSession") != null) {
            cidadevo.setEstado(Long.valueOf(req.getParameter("estadoSession")));


        }

        if (req.getParameter("cidadeSession") != "" && req.getParameter("cidadeSession") != null) {
            cidade = cidadeController.getOne(Long.valueOf(req.getParameter("cidadeSession")));

            cidadevo.setCep(cidade.getCep());
            cidadevo.setCod(cidade.getCod());
            cidadevo.setId(cidade.getId());
            cidadevo.setNome(cidade.getNome());

            bairrovo.setCidade(cidade.getId());
            logradourovo.setCidade(cidade.getId());


        }


        if (req.getParameter("numero") != "" && req.getParameter("numero") != null) {
            numero = Integer.parseInt(req.getParameter("numero"));
            vo.setNumero(numero);

        }

        if (req.getParameter("bairro") != "" && req.getParameter("bairro") != null) {
            bairro = bairroController.getOne(Long.valueOf(req.getParameter("bairro")));
            vo.setBairro(bairro.getId());

            bairrovo.setNome(bairro.getNome());
            bairrovo.setId(bairro.getId());
        }

        if (req.getParameter("tipologradouro") != "" && req.getParameter("tipologradouro") != null) {
            tipoLogradouro = tipoLogradouroController.getOne(Long.valueOf(req.getParameter("tipologradouro")));
            logradourovo.setTipologradouro(tipoLogradouro.getId());

        }


        if (req.getParameter("logradouro") != "" && req.getParameter("logradouro") != null) {

            String nomeLogradouro = req.getParameter("logradouro");
            logradourovo.setNome(nomeLogradouro);

            if (cidade != null
                    && tipoLogradouro != null) {


                logradourovo.setCidade(cidade.getId());

                logradourovo.setTipologradouro(tipoLogradouro.getId());

                List<Logradouro> validation = logradouroController.filtrar(logradourovo);

                if (validation.size() == 0) {
                    logradouro = new Logradouro();

                    logradouro.setCidade(cidade);
                    logradouro.setNome(nomeLogradouro);
                    logradouro.setTipologradouro(tipoLogradouro);

                    logradouroController.save(logradouro);

                    validation = logradouroController.filtrar(logradourovo);

                }

                logradouro = validation.get(0);

            }
        }


        Endereco endereco = new Endereco();
        endereco.setNumero(numero);
        endereco.setCep(cep);
        endereco.setComplemento(complemento);
        endereco.setBairro(bairro);
        endereco.setLogradouro(logradouro);

        List<String> erros = enderecoController.validation(endereco);

        vo.setCep(endereco.getCep());
        vo.setComplemento(endereco.getComplemento());


        if (erros.size() == 0) {


            List<Endereco> verifica = enderecoController.filtrar(vo);


            if (verifica.size() > 0 && vo.getComplemento() == "") {
                int compl = 0;

                for (int i = 0; i < verifica.size(); i++) {
                    if (verifica.get(i).getComplemento().equals("")) {
                        compl = compl + 1;
                    }
                }
                if (compl == 0) {
                    verifica.clear();
                }
            }


            if (verifica.size() == 0) {

                enderecoController.save(endereco);

                vo.setId(null);
                vo.setNumero(null);
                vo.setCep("");
                vo.setComplemento("");
                vo.setBairro(null);
                vo.setLogradouro(null);

                bairrovo.setCidade(null);
                bairrovo.setNome("");
                bairrovo.setId(null);

                cidadevo.setEstado(null);
                cidadevo.setNome("");
                cidadevo.setCod("");
                cidadevo.setCep("");
                cidadevo.setId(null);

                session.setAttribute("listaEndereco", enderecoController.filtrar(vo));
                session.setAttribute("filtroCidade_insereEndereco", cidadevo);
                session.setAttribute("listaCidade_insereEndereco", cidadeController.filtrar(cidadevo));
                session.setAttribute("filtroBairro_insereEndereco", bairrovo);
                session.setAttribute("listaBairro_insereEndereco", bairroController.filtrar(bairrovo));
                session.setAttribute("success", "Endereço inserido com sucesso");
                session.setAttribute("enderecoParaInserir", null);

                res.sendRedirect("/views/endereco/lista.jsp");
            } else {

                String existe = "Endereço já cadastrado!";

                session.setAttribute("errors", existe);
                session.setAttribute("filtroCidade_insereEndereco", cidadevo);
                session.setAttribute("listaCidade_insereEndereco", cidadeController.filtrar(cidadevo));
                session.setAttribute("filtroBairro_insereEndereco", bairrovo);
                session.setAttribute("listaBairro_insereEndereco", bairroController.filtrar(bairrovo));
                session.setAttribute("enderecoParaInserir", vo);
                session.setAttribute("filtroLogradouro_insereEndereco", logradourovo);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/endereco/insere.jsp");
                requestDispatcher.forward(req, res);

            }

        } else {
            session.setAttribute("errors", erros);
            session.setAttribute("filtroCidade_insereEndereco", cidadevo);
            session.setAttribute("listaCidade_insereEndereco", cidadeController.filtrar(cidadevo));
            session.setAttribute("filtroBairro_insereEndereco", bairrovo);
            session.setAttribute("listaBairro_insereEndereco", bairroController.filtrar(bairrovo));
            session.setAttribute("enderecoParaInserir", vo);
            session.setAttribute("filtroLogradouro_insereEndereco", logradourovo);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/endereco/insere.jsp");
            requestDispatcher.forward(req, res);
        }

    }

}
