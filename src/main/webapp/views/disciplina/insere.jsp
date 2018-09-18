<%@ page import="br.com.sonner.estagio.model.parte2.segundo.Disciplina" %>
<%@page contentType="text/html; charset=iso-8859-1"
        pageEncoding="iso-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="disciplinaController"
             class="br.com.sonner.estagio.controller.DisciplinaControllerImpl"></jsp:useBean>

<%
    Disciplina campoDisciplina = (Disciplina) session.getAttribute("campoDisciplina");

    if (campoDisciplina == null) {
        campoDisciplina = new Disciplina();
        campoDisciplina.setNome("");
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
            titulo="Inserir nova disciplina"
            actionSalvar="true"
            formId="insere-form"
            actionFechar="true">
    </sge:header>
    <div class="div-form">
        <form action="/insere-disciplina" method="post" id="insere-form" style="width: 60%; margin: auto">
            <div class="div-form">
                <div class="form-row">
                    <div>Nome:</div>
                    <input type="text" name="nome" class="form-control" value="<%= campoDisciplina.getNome() %>"
                           onkeypress="return validString(String.fromCharCode(window.event.keyCode))"></div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
<% session.setAttribute("campoDisciplina", null); %>