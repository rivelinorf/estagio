package br.com.sonner.estagio.servlet.escola;

import br.com.sonner.estagio.controller.*;
import br.com.sonner.estagio.controller.api.EnderecoController;
import br.com.sonner.estagio.model.*;
import br.com.sonner.estagio.model.Escola;
import br.com.sonner.estagio.vos.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/insere-escola")
public class Insere extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        CidadeControllerImpl cidadeController = new CidadeControllerImpl();
        CidadeFiltroVO cidadevo = new CidadeFiltroVO();
        BairroControllerImpl bairroController = new BairroControllerImpl();
        BairroFiltroVO bairrovo = new BairroFiltroVO();
        Estado estado;
        Cidade cidade;
        Endereco endereco;
        EstadoControllerImpl estadoController = new EstadoControllerImpl();
        LogradouroControllerImpl logradouroController = new LogradouroControllerImpl();
        EnderecoController enderecoController = new EnderecoControllerImpl();
        EnderecoFiltroVO enderecovo = new EnderecoFiltroVO();

        LogradouroFiltroVO logradourovo = new LogradouroFiltroVO();

        cidadevo.setEstado(null);
        cidadevo.setNome("");
        cidadevo.setCod("");
        cidadevo.setCep("");
        cidadevo.setId(null);

        enderecovo.setBairro(null);
        enderecovo.setCep("");
        enderecovo.setComplemento("");
        enderecovo.setNumero(null);
        enderecovo.setLogradouro(null);
        enderecovo.setId(null);

        bairrovo.setCidade(null);
        bairrovo.setNome("");
        bairrovo.setId(null);

        logradourovo.setNome("");
        logradourovo.setCidade(null);
        logradourovo.setTipologradouro(null);

        HttpSession session = request.getSession();

        if (request.getParameter("cep") != "" && request.getParameter("cep") != null) {
            enderecovo.setCep(request.getParameter("cep"));
            List<Endereco> enderecos = enderecoController.filtrar(enderecovo);
            if (enderecos.size() > 0) {
                endereco = enderecos.get(0);
                cidade = endereco.getLogradouro().getCidade();
                estado = endereco.getLogradouro().getCidade().getEstado();


                if (enderecos.size() >= 1 & !cidade.getCep().equals(endereco.getCep())) {

                    enderecovo.setCep(endereco.getCep());

                    Logradouro logradouro = logradouroController.getOne(endereco.getLogradouro().getId());
                    Bairro bairro = bairroController.getOne(endereco.getBairro().getId());
                    estado = estadoController.getOne(cidade.getEstado().getId());

                    cidadevo.setId(cidade.getId());
                    cidadevo.setNome(cidade.getNome());
                    cidadevo.setCep(cidade.getCep());
                    cidadevo.setCod(cidade.getCod());
                    cidadevo.setEstado(cidade.getEstado().getId());


                    logradourovo.setId(logradouro.getId());
                    logradourovo.setNome(logradouro.getNome());
                    logradourovo.setCidade(logradouro.getCidade().getId());
                    logradourovo.setTipologradouro(logradouro.getTipologradouro().getId());

                    bairrovo.setId(bairro.getId());
                    bairrovo.setNome(bairro.getNome());
                    bairrovo.setCidade(bairro.getCidade().getId());

                    session.setAttribute("filtroCidade_insereEndereco", cidadevo);
                    session.setAttribute("listaCidade_insereEndereco", cidadeController.filtrar(cidadevo));
                    session.setAttribute("filtroLogradouro_insereEndereco", logradourovo);
                    session.setAttribute("filtroBairro_insereEndereco", bairrovo);
                    session.setAttribute("listaBairro_insereEndereco", bairroController.filtrar(bairrovo));
                    session.setAttribute("enderecoParaInserir", enderecovo);
                    session.setAttribute("estado", estado);
                    session.setAttribute("cidade", cidade);
                    session.setAttribute("validaCep", 1);

                } else if (enderecos.size() >= 1 & cidade.getCep().equals(endereco.getCep())) {
                    bairrovo.setCidade(cidade.getId());
                    bairrovo.setId(null);

                    cidadevo.setEstado(cidade.getEstado().getId());

                    enderecovo.setBairro(bairrovo.getId());
                    enderecovo.setCep(endereco.getCep());

                    session.setAttribute("enderecoParaInserir", enderecovo);
                    session.setAttribute("filtroLogradouro_insereEndereco", logradourovo);
                    session.setAttribute("filtroCidade_insereEndereco", cidadevo);
                    session.setAttribute("listaCidade_insereEndereco", cidadeController.filtrar(cidadevo));
                    session.setAttribute("filtroBairro_insereEndereco", bairrovo);
                    session.setAttribute("listaBairro_insereEndereco", bairroController.filtrar(bairrovo));
                    session.setAttribute("cidade", cidade);
                    session.setAttribute("estado", estado);
                    session.setAttribute("validaCep", 2);


                }
            } else {
                String existe = "CEP não encontrado!";

                session.setAttribute("errors", existe);

                session.setAttribute("enderecoParaInserir", enderecovo);
                session.setAttribute("filtroLogradouro_insereEndereco", null);
                session.setAttribute("filtroCidade_insereEndereco", null);
                session.setAttribute("listaCidade_insereEndereco", null);
                session.setAttribute("filtroBairro_insereEndereco", null);
                session.setAttribute("listaBairro_insereEndereco", null);
                session.setAttribute("cidade", null);
                session.setAttribute("estado", null);
                session.setAttribute("validaCep", 3);

            }
        } else {
            if (request.getParameter("estado") != "" && request.getParameter("estado") != null) {
                cidadevo.setEstado(Long.valueOf(request.getParameter("estado")));
                estado = estadoController.getOne(cidadevo.getEstado());

                session.setAttribute("filtroCidade_insereEndereco", cidadevo);
                session.setAttribute("listaCidade_insereEndereco", cidadeController.filtrar(cidadevo));
                session.setAttribute("listaBairro_insereEndereco", null);
                session.setAttribute("filtroBairro_insereEndereco", null);
                session.setAttribute("estado", estado);

            }

            if (request.getParameter("cidade") != "" && request.getParameter("cidade") != null) {
                Long id = Long.valueOf(request.getParameter("cidade"));
                cidade = cidadeController.getOne(id);

                bairrovo.setCidade(cidade.getId());

                cidadevo.setEstado(cidade.getEstado().getId());

                enderecovo.setBairro(bairrovo.getId());


                session.setAttribute("filtroCidade_insereEndereco", cidadevo);
                session.setAttribute("listaCidade_insereEndereco", cidadeController.filtrar(cidadevo));
                session.setAttribute("filtroBairro_insereEndereco", bairrovo);
                session.setAttribute("listaBairro_insereEndereco", bairroController.filtrar(bairrovo));
                session.setAttribute("cidade", cidade);

            }

            if (cidadevo.getEstado() == null && bairrovo.getCidade() == null) {

                if (session.getAttribute("cidade") == null) {
                    session.setAttribute("estado", null);
                    session.setAttribute("filtroCidade_insereEndereco", null);
                }

                session.setAttribute("cidade", null);
                session.setAttribute("filtroBairro_insereEndereco", null);
                session.setAttribute("listaBairro_insereEndereco", null);

            }
        }

        response.sendRedirect("/views/escola/insere.jsp");
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        EscolaControllerImpl escolaController = new EscolaControllerImpl();
        LogradouroControllerImpl logradouroController = new LogradouroControllerImpl();
        BairroControllerImpl bairroController = new BairroControllerImpl();
        EnderecoControllerImpl enderecoController = new EnderecoControllerImpl();
        CidadeControllerImpl cidadeController = new CidadeControllerImpl();
        EstadoControllerImpl estadoController = new EstadoControllerImpl();
        TipoLogradouroControllerImpl tipoLogradouroController = new TipoLogradouroControllerImpl();

        EscolaFiltroVO escolavo = new EscolaFiltroVO();
        EnderecoFiltroVO vo = new EnderecoFiltroVO();
        BairroFiltroVO bairrovo = new BairroFiltroVO();
        CidadeFiltroVO cidadevo = new CidadeFiltroVO();
        LogradouroFiltroVO logradourovo = new LogradouroFiltroVO();

        BairroFiltroVO bairrovo2 = new BairroFiltroVO();
        CidadeFiltroVO cidadevo2 = new CidadeFiltroVO();

        bairrovo2.setCidade(null);
        cidadevo2.setEstado(null);

        HttpSession session = req.getSession();
        Escola novaEscola = new Escola();

        String nome = req.getParameter("nome");
        Endereco endereco = new Endereco();

        String cep = req.getParameter("cepSession");
        String complemento = req.getParameter("complemento");
        Integer numero = null;
        Logradouro logradouro = null;
        Bairro bairro = null;
        TipoLogradouro tipoLogradouro = null;
        Cidade cidade = null;
        Estado estado = null;

        if (req.getParameter("estado") != "" && req.getParameter("estado") != null) {
            cidadevo.setEstado(Long.valueOf(req.getParameter("estado")));
            cidadevo2.setEstado(cidadevo.getEstado());


        }

        if (req.getParameter("cidade") != "" && req.getParameter("cidade") != null) {
            cidade = cidadeController.getOne(Long.valueOf(req.getParameter("cidade")));

            cidadevo.setCep(cidade.getCep());
            cidadevo.setCod(cidade.getCod());
            cidadevo.setId(cidade.getId());
            cidadevo.setNome(cidade.getNome());

            bairrovo.setCidade(cidade.getId());
            logradourovo.setCidade(cidade.getId());

            bairrovo2.setCidade(cidade.getId());


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


        endereco.setCep(cep);
        endereco.setComplemento(complemento);
        endereco.setBairro(bairro);
        endereco.setLogradouro(logradouro);
        endereco.setNumero(numero);

        List<String> erros = enderecoController.validation(endereco);

        vo.setCep(endereco.getCep());
        vo.setComplemento(endereco.getComplemento());
        vo.setLogradouro(logradouro.getId());


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
                verifica = enderecoController.filtrar(vo);

            }
            endereco = verifica.get(0);

        }

        novaEscola.setNome(nome);
        novaEscola.setEndereco(endereco);

        List<String> errosEscola = escolaController.validation(novaEscola);
        escolavo.setNome(nome);
        escolavo.setEndereco(endereco.getId());

        if (errosEscola.size() == 0) {

            escolavo.setNome("");

            List<Escola> verifica = escolaController.filtrar(escolavo);

            if (verifica.size() == 0) {
                escolaController.save(novaEscola);
                escolavo.setEndereco(null);
                escolavo.setId(null);

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


                session.setAttribute("listaEscola", escolaController.filtrar(escolavo));
                session.setAttribute("filtroCidade_insereEndereco", cidadevo);
                session.setAttribute("listaCidade_insereEndereco", cidadeController.filtrar(cidadevo));
                session.setAttribute("filtroBairro_insereEndereco", bairrovo);
                session.setAttribute("listaBairro_insereEndereco", bairroController.filtrar(bairrovo));
                session.setAttribute("success", "Escola inserido com sucesso");
                session.setAttribute("enderecoParaInserir", null);
                session.setAttribute("escolaParaInserir", null);

                res.sendRedirect("/views/escola/lista.jsp");
            } else {
                String existe = "Escola já cadastrado!";
                escolavo.setNome(nome);

                session.setAttribute("errors", existe);
                session.setAttribute("filtroCidade_insereEndereco", cidadevo);
                session.setAttribute("listaCidade_insereEndereco", cidadeController.filtrar(cidadevo2));
                session.setAttribute("filtroBairro_insereEndereco", bairrovo);
                session.setAttribute("listaBairro_insereEndereco", bairroController.filtrar(bairrovo2));
                if (cidade == null) {
                    session.setAttribute("listaBairro_insereEndereco", null);
                }
                session.setAttribute("enderecoParaInserir", vo);
                session.setAttribute("escolaParaInserir", escolavo);
                session.setAttribute("filtroLogradouro_insereEndereco", logradourovo);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/escola/insere.jsp");
                requestDispatcher.forward(req, res);
            }

        } else {
            session.setAttribute("errors", errosEscola);
            session.setAttribute("filtroCidade_insereEndereco", cidadevo);
            session.setAttribute("listaCidade_insereEndereco", cidadeController.filtrar(cidadevo2));
            session.setAttribute("filtroBairro_insereEndereco", bairrovo);
            session.setAttribute("listaBairro_insereEndereco", bairroController.filtrar(bairrovo2));
            if (cidade == null) {
                session.setAttribute("listaBairro_insereEndereco", null);
            }
            session.setAttribute("enderecoParaInserir", vo);
            session.setAttribute("escolaParaInserir", escolavo);
            session.setAttribute("filtroLogradouro_insereEndereco", logradourovo);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/escola/insere.jsp");
            requestDispatcher.forward(req, res);
        }


    }
}