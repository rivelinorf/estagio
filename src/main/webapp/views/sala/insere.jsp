<%@ page import="br.com.sonner.estagio.model.parte2.segundo.Sala" %>
<%@page contentType="text/html; charset=iso-8859-1"
        pageEncoding="iso-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="salaController"
             class="br.com.sonner.estagio.controller.SalaControllerImpl"></jsp:useBean>
<jsp:useBean id="escolaController"
             class="br.com.sonner.estagio.controller.EscolaControllerImpl"></jsp:useBean>

<%
    Sala campoSala = (Sala) session.getAttribute("campoSala");
    if (campoSala == null) {
        campoSala = new Sala();
        campoSala.setNome("");
        campoSala.setEscola(null);
    }
%>

<html>
<head>
    <jsp:include page="/includes/head.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/includes/menu.jsp"></jsp:include>
<div class="main">
    <sge:header
            titulo="Inserir nova Sala"
            actionSalvar="true"
            formId="insere-form"
            actionFechar="true">
    </sge:header>

    <div class="div-form">
        <form action="/insere-sala" method="post" id="insere-form" style="width: 60%; margin: auto">

            <div class="form-row">
                <div>Nome:</div>
                <input type="text" name="sala" class="form-control" value="<%= campoSala.getNome() %>"
                       maxlength="60" onkeypress="return validString(String.fromCharCode(window.event.keyCode))">
            </div>

            <div class="form-row">
                <div>Escola:</div>
                <select name="escola" class="form-control" style="background-color: rgb(46, 46, 46)">
                    <option value="">Selecione uma opção...</option>
                    <c:forEach items="${escolaController.all}" var="escola">
                        <option value="${escola.id}">${escola.nome}</option>
                    </c:forEach>
                </select>
            </div>
        </form>
    </div>

</body>
</html>
<% session.setAttribute("campoSala", null); %>