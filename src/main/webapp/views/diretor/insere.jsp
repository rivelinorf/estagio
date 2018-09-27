<%@ page import="br.com.sonner.estagio.vos.EnderecoFiltroVO" %>
<%@page contentType="text/html; charset=iso-8859-1"
        pageEncoding="iso-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="escolaController" class="br.com.sonner.estagio.controller.EscolaControllerImpl"></jsp:useBean>

<%
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
</head>
<body>
<jsp:include page="/includes/menu.jsp"></jsp:include>
<div class="main">
    <sge:header titulo="Inserir novo Diretor" actionSalvar="true"
                formId="insere-form" actionFechar="true">
    </sge:header>
    <div class="div-form">
        <form action="/insere-diretor" method="post" id="insere-form"
              style="width: 60%; margin: auto">
            <jsp:include page="/includes/pessoaComponete.jsp"></jsp:include>
            <div class="form-row">
                <div>Data de admissão:</div>
                <input type="text" name="data-admissao" class="form-control" style="background-color: rgb(46, 46, 46)">
            </div>
            <div class="form-row">
                <div>Escola:</div>
                <select name="escola" class="form-control">
                    <option value="">Selecione uma opção...</option>
                    <c:forEach items="${escolaController.all}" var="escola">
                        <option value="${escola.id}">${escola.nome}</option>
                    </c:forEach>
                </select>
            </div>
        </form>

    </div>
</div>
</body>
</html>