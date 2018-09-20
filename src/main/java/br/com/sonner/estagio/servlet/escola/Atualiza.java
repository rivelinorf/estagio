package br.com.sonner.estagio.servlet.escola;

import br.com.sonner.estagio.controller.*;
import br.com.sonner.estagio.model.*;
import br.com.sonner.estagio.model.Escola;
import br.com.sonner.estagio.vos.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class Atualiza {
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
        Endereco endereco = null;

        String cep = req.getParameter("cep");
        String complemento = req.getParameter("complemento");
        Integer numero = null;
        Logradouro logradouro = null;
        Bairro bairro = null;
        TipoLogradouro tipoLogradouro = null;
        Cidade cidade = null;
        Estado estado = null;

        if (req.getParameter("estadoSession") != "" && req.getParameter("estadoSession") != null) {
            cidadevo.setEstado(Long.valueOf(req.getParameter("estadoSession")));
            cidadevo2.setEstado(cidadevo.getEstado());


        }

        if (req.getParameter("cidadeSession") != "" && req.getParameter("cidadeSession") != null) {
            cidade = cidadeController.getOne(Long.valueOf(req.getParameter("cidadeSession")));

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

        endereco.setNumero(numero);
        endereco.setCep(cep);
        endereco.setComplemento(complemento);
        endereco.setBairro(bairro);
        endereco.setLogradouro(logradouro);

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
        novaEscola.setId(Long.valueOf(req.getParameter("id")));


        List<String> errosEscola = escolaController.validation(novaEscola);
        escolavo.setNome(nome);
        escolavo.setEndereco(endereco.getId());
        escolavo.setId(novaEscola.getId());

        if (errosEscola.size() == 0) {
            List<Endereco> verifica = enderecoController.filtrar(vo);
            if (verifica.size() == 0) {
                escolaController.save(novaEscola);

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
                session.setAttribute("escolaParaInserir", null);

                res.sendRedirect("/views/escola/lista.jsp");
            } else {
                String existe = "Aluno já cadastrado!";

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
