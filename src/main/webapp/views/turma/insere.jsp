<%@ page import="br.com.sonner.estagio.model.Turma" %>
<%@page contentType="text/html; charset=iso-8859-1"
        pageEncoding="iso-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="turmaController"
             class="br.com.sonner.estagio.controller.TurmaControllerImpl"></jsp:useBean>
<jsp:useBean id="escolaController"
             class="br.com.sonner.estagio.controller.EscolaControllerImpl"></jsp:useBean>

<%
    Turma campoTurma = (Turma) session.getAttribute("campoTurma");
    if (campoTurma == null) {
        campoTurma = new Turma();
        campoTurma.setNome("");
        campoTurma.setEscola(null);
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
            titulo="Inserir nova Turma"
            actionSalvar="true"
            formId="insere-form"
            actionFechar="true">
    </sge:header>

    <div class="div-form">
        <form action="/insere-turma" method="post" id="insere-form" style="width: 60%; margin: auto">

            <div class="form-row">
                <div>Turma:</div>
                <input type="text" name="turma" class="form-control" value="<%= campoTurma.getNome() %>"
                       maxlength="60">
            </div>

            <div class="form-row">
                <div>Escola:</div>
                <select name="escola" class="form-control" style="background-color: rgb(46, 46, 46)">
                    <option value="">Selecione uma op��o...</option>
                    <c:forEach items="${escolaController.all}" var="escola">
                        <c:choose>
                            <c:when test="${escola.id == campoTurma.escola.id}">
                                <option value="${escola.id}" selected>${escola.nome}</option>
                            </c:when>
                            <c:otherwise>
                            <option value="${escola.id}">${escola.nome}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
        </form>
    </div>

</body>
</html>
<% session.setAttribute("campoTurma", null); %>