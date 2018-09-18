<%@ page import="br.com.sonner.estagio.model.Estado" %>
<%@page contentType="text/html; charset=iso-8859-1"
        pageEncoding="iso-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="estados"
             class="br.com.sonner.estagio.controller.EstadoControllerImpl"></jsp:useBean>
<%
    Estado campoEstado = (Estado) session.getAttribute("campoEstado");

    if (campoEstado == null) {
        campoEstado = new Estado();
        campoEstado.setNome("");
        campoEstado.setAbv("");
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
    <sge:header titulo="Inserir novo Estado" actionSalvar="true"
                formId="insere-form" actionFechar="true">
    </sge:header>
    <div class="div-form">
        <form action="/insere-estado" method="post" id="insere-form"
              style="width: 60%; margin: auto">
            <div class="form-row">
                <div>Nome:</div>
                <input type="text" value="<%= campoEstado.getNome() %>" name="nome" class="form-control" maxlength="50"
                       onkeypress="return validString(String.fromCharCode(window.event.keyCode))">
            </div>
            <div class="form-row">
                <div>Abreviação:</div>
                <input type="text" value="<%= campoEstado.getAbv() %>" name="abv" class="form-control" maxlength="2"
                       onkeypress="return validString(String.fromCharCode(window.event.keyCode))">
            </div>
        </form>
    </div>
</div>
</body>
</html>
<% session.setAttribute("campoEstado", null); %>