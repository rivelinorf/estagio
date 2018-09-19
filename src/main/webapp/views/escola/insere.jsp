<%@ page import="br.com.sonner.estagio.model.parte2.segundo.Escola" %>
<%@ page import="br.com.sonner.estagio.vos.EscolaFiltroVO" %>
<%@ page import="br.com.sonner.estagio.vos.EnderecoFiltroVO" %>
<%@page contentType="text/html; charset=iso-8859-1"
        pageEncoding="iso-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="escolas"
             class="br.com.sonner.estagio.controller.EscolaControllerImpl"></jsp:useBean>
<%
    EscolaFiltroVO campoEscola = (EscolaFiltroVO) session.getAttribute("escolaParaInserir");

    if (campoEscola == null) {
        campoEscola = new EscolaFiltroVO();
        campoEscola.setNome("");
        campoEscola.setEndereco(null);
    }

    EnderecoFiltroVO campoEndereco = (EnderecoFiltroVO) session.getAttribute("enderecoParaInserir");
    if (campoEndereco == null) {
        campoEndereco = new EnderecoFiltroVO();
        campoEndereco.setNumero(null);
        campoEndereco.setCep("");
        campoEndereco.setComplemento("");
        campoEndereco.setLogradouro(null);
        campoEndereco.setBairro(null);
    }
%>


<html>
<head>
    <jsp:include page="/includes/head.jsp"></jsp:include>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
    <script type="text/javascript" src="/assets/js/MascaraValidacao.js"></script>
</head>
<body>
<jsp:include page="/includes/menu.jsp"></jsp:include>
<div class="main">
    <sge:header titulo="Inserir nova Escola" actionSalvar="true"
                formId="insere-form" actionFechar="true">
    </sge:header>

    <div class="div-form">
        <form action="/insere-escola" method="get" id="filter-form"
              style="width: 100%;">

            <div class="form-row">
            <div>CEP:</div>
            <input type="text" name="cep" placeholder="Ex.: 00.000-000"
                   class="form-control" value="<%=campoEndereco.getCep()%>"
                   maxlength="10" style="width: 20.35%;">


        </form>
        <button class="main-btn btn-editar" type="submit" form="filter-form" value="Submit">Buscar</button>
    </div>

        <form action="/insere-escola" method="post" id="insere-form"
              style="width: 60%; margin: auto">
            <jsp:include page="/includes/enderecoComponente.jsp"></jsp:include>
            <div class="form-row">

                <div>Nome:</div>
                <input type="text" value="<%= campoEscola.getNome() %>" name="nome" class="form-control" maxlength="50"
                       onkeypress="return validString(String.fromCharCode(window.event.keyCode))">
            </div>
        </form>
    </div>
</div>
</body>
</html>
<% session.setAttribute("campoEscola", null); %>