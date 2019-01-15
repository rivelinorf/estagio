<%@ page import="br.com.sonner.estagio.model.Disciplina" %>
<%@page contentType="text/html; charset=iso-8859-1"
        pageEncoding="iso-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="disciplinaController"
             class="br.com.sonner.estagio.controller.DisciplinaControllerImpl"></jsp:useBean>
<jsp:useBean id="escolaController"
             class="br.com.sonner.estagio.controller.EscolaControllerImpl"></jsp:useBean>

<%
    Disciplina campoDisciplina = (Disciplina) session.getAttribute("campoDisciplina");
    if (campoDisciplina == null) {
        campoDisciplina = new Disciplina();
        campoDisciplina.setNome("");
        campoDisciplina.setEscola(null);
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
            titulo="Inserir nova Disciplina"
            actionSalvar="true"
            formId="insere-form"
            actionFechar="true">
    </sge:header>

    <div class="div-form">
        <form action="/insere-disciplina" method="post" id="insere-form" style="width: 60%; margin: auto">

            <div class="form-row">
                <div>Disciplina:</div>
                <input type="text" name="disciplina" class="form-control" value="<%= campoDisciplina.getNome() %>"
                       maxlength="60">
            </div>

            <div class="form-row">
                <div>Escola:</div>
                <select name="escola" class="form-control" onchange="location.href = '/busca-turma?escola='+this.value">
                    <option value="0">Selecione uma opção...</option>
                    <c:forEach items="${escolaController.all}" var="escola">
                        <c:choose>
                            <c:when test="${escola.id == escolaVo.id}">
                                <option value="${escola.id}" selected>${escola.nome}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${escola.id}">${escola.nome}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
            <div class="form-row">
                <div>Turma:</div>
                <select name="turma" class="form-control">
                    <option value="">Selecione uma opção...</option>
                    <c:forEach items="${turmaEscola}" var="turma">
                        <option value="${turma.id}">${turma.nome}</option>
                    </c:forEach>
                </select>
            </div>
        </form>
    </div>

</body>
</html>
<% session.setAttribute("campoDisciplina", null); %>