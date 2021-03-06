<%@ page import="br.com.sonner.estagio.model.TipoLogradouro" %>
<%@page contentType="text/html; charset=iso-8859-1"
        pageEncoding="iso-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="tipologradouroController"
             class="br.com.sonner.estagio.controller.TipoLogradouroControllerImpl"></jsp:useBean>

<%
    TipoLogradouro campoTipologradouro = (TipoLogradouro) session.getAttribute("campoTipologradouro");

    if (campoTipologradouro == null) {
        campoTipologradouro = new TipoLogradouro();
        campoTipologradouro.setNome("");
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
    <sge:header
            titulo="Inserir novo Tipo de Logradouro"
            actionSalvar="true"
            formId="insere-form"
            actionFechar="true">
    </sge:header>
    <div class="div-form">
        <form action="/insere-tipologradouro" method="post" id="insere-form" style="width: 60%; margin: auto">
            <div class="div-form">
                <div class="form-row">
                    <div>Nome:</div>
                    <input type="text" name="nome" class="form-control" value="<%= campoTipologradouro.getNome() %>"
                           onkeypress="return validString(String.fromCharCode(window.event.keyCode))"></div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
<% session.setAttribute("campoTipologradouro", null); %>