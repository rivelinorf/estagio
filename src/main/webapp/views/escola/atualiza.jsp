<%@ page import="br.com.sonner.estagio.vos.EnderecoFiltroVO" %>
<%@ page import="br.com.sonner.estagio.vos.EscolaFiltroVO" %>
<%@page contentType="text/html; charset=iso-8859-1"
        pageEncoding="iso-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="escolas"
             class="br.com.sonner.estagio.controller.EscolaControllerImpl"></jsp:useBean>
<%
    EscolaFiltroVO campoEscola = (EscolaFiltroVO) session.getAttribute("escolaParaEditar");

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
    <sge:header titulo="Editar Escola" actionSalvar="true"
                formId="insere-form" actionFechar="true">
    </sge:header>


    <form name="form1" action="/atualiza-escola" method="get" id="filter-form" style="width: 100%;">

        <div class="form-row">
            <div>CEP:</div>
            <input type="text" name="cep" class="form-control"
                   value="<%=campoEndereco.getCep()%>" onKeyPress="MascaraCep(form1.cep);"
                   maxlength="10" onKeyPress="mascaraInteiro()" placeholder="Ex.: 00.000-000" style="width: 150px;">
            <button class="main-btn btn-editar" type="submit" form="filter-form" value="Submit">Buscar</button>
        </div>
    </form>



    <form action="/atualiza-escola" method="post" id="insere-form">
        <jsp:include page="/includes/enderecoComponente.jsp"></jsp:include>
        <input type="hidden" value="<%=campoEndereco.getCep()%>" name="cepSession">
        <input type="hidden" value="<%=campoEscola.getId()%>" name="id">
        <div class="form-row">

            <div>Nome:</div>
            <input type="text" value="<%= campoEscola.getNome() %>" name="nome" class="form-control" maxlength="50"
                   onkeypress="return validString(String.fromCharCode(window.event.keyCode))">
        </div>
    </form>
</div>

</body>
</html>
