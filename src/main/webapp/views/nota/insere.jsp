<%@ page import="br.com.sonner.estagio.model.Nota" %>
<%@page contentType="text/html; charset=iso-8859-1"
        pageEncoding="iso-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="alunos"
             class="br.com.sonner.estagio.controller.AlunoControllerImpl"></jsp:useBean>
<jsp:useBean id="turmas"
             class="br.com.sonner.estagio.controller.TurmaControllerImpl"></jsp:useBean>
<jsp:useBean id="disciplinas"
             class="br.com.sonner.estagio.controller.DisciplinaControllerImpl"></jsp:useBean>

<%
    Nota nota = (Nota) session.getAttribute("campoNota");

    if (nota == null) {
        nota = new Nota();
        nota.setAluno(null);
        nota.setId(null);
        nota.setTurmaDisciplina(null);
        nota.setNota(null);
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
    <sge:header titulo="Lançar nota" page=""
                actionFiltrar="true" actionNovo="/views/nota/insere.jsp"
                formId="filter-form" actionFechar="true">
    </sge:header>


    <div class="div-form">
        <form name="form1" action="/insere-nota" method="get"
              id="insere-form" style="width: 100%;">
            <div class="form-row">
                <div>Disciplina:</div>
                <select name="disciplina" class="form-control"
                        style="background-color: rgb(46, 46, 46)">
                    <option value="">Selecione uma opção...</option>
                    <c:forEach items="${disciplinas.all}" var="disciplina">
                        <c:choose>
                            <c:when test="${disciplina.id == campoNota.disciplina}">
                                <option value="${disciplina.id}" selected>${disciplina.nome}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${disciplina.id}">${disciplina.nome}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>

            <div class="form-row">
                <div>Turma:</div>
                <select name="turma" class="form-control"
                        style="background-color: rgb(46, 46, 46)">
                    <option value="">Selecione uma opção...</option>
                    <c:forEach items="${turmas.all}" var="turma">
                        <c:choose>
                            <c:when test="${turma.id == campoNota.turma}">
                                <option value="${turma.id}" selected>${turma.nome}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${turma.id}">${turma.nome}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
        </form>
    </div>

    <table class="table">
        <thead>
        <tr>
            <th>Matricula</th>
            <th>Nome</th>
            <th>Nota</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listaAluno}" var="aluno">
            <tr>
                <td>${aluno.matricula}</td>
                <td>${aluno.nome}</td>
                <td>
                    <%
                        if (nota.getNota() == null) {
                    %>
                    <input type="text" name="nota" class="form-control" maxlength="50"
                           onkeypress="return validString(String.fromCharCode(window.event.keyCode))">
                    <%
                    } else {
                    %>
                    <input type="text" value="<%= nota.getNota() %>" name="nota" class="form-control" maxlength="50"
                           onkeypress="return validString(String.fromCharCode(window.event.keyCode))">
                    <%
                        }
                    %>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


</div>
</body>
</html>

<% session.setAttribute("campoNota", null); %>