<%@ page import="br.com.sonner.estagio.model.Nota" %>
<%@ page import="br.com.sonner.estagio.model.TurmaDisciplina" %>
<%@ page import="br.com.sonner.estagio.vos.TurmaDisciplinaFiltroVO" %>
<%@ page import="br.com.sonner.estagio.model.Turma" %>
<%@ page import="br.com.sonner.estagio.model.Disciplina" %>
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

    Turma turma = (Turma) session.getAttribute("turma");

    if (turma == null) {
        turma = new Turma();
        turma.setEscola(null);
        turma.setNome(null);
        turma.setId(null);
    }

    Disciplina disciplina = (Disciplina) session.getAttribute("disciplina");

    if (disciplina == null) {
        disciplina = new Disciplina();
        disciplina.setEscola(null);
        disciplina.setNome(null);
        disciplina.setId(null);
    }

    Integer campoNota = (Integer) session.getAttribute("campoNota");
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
                actionFiltrar="true" actionLancar="true" formIdlancar="insere-form"
                formId="filter-form" actionFechar="true">
    </sge:header>


    <div class="div-form">
        <form action="/pesquisa-nota" method="get" id="filter-form"
              style="width: 1000px;">
            <div class="form-row">
                <div>Disciplina:</div>
                <select name="disciplina" class="form-control"
                        style="background-color: rgb(46, 46, 46)">
                    <option value="">Selecione uma opção...</option>
                    <c:forEach items="${disciplinas.all}" var="disciplina">
                        <c:choose>
                            <c:when test="${disciplina.id == filtroTurmaDisciplina.disciplina}">
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
                            <c:when test="${turma.id == filtroTurmaDisciplina.turma}">
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
    <div class="content">
        <form name="form1" action="/insere-nota" method="post"
              id="insere-form">
            <table class="table">
                <thead>
                <tr>
                    <th>Matricula</th>
                    <th>Nome</th>
                    <th>Turma</th>


                </tr>
                </thead>
                <tbody>
                <c:forEach items="${listaAluno}" var="aluno">

                    <tr>

                        <td>${aluno.matricula.numero}</td>
                        <td>${aluno.pessoa.nome}</td>

                        <c:forEach items="${listaNota}" var="nota">
                            <c:choose>
                                <c:when test="${aluno.pessoa.nome == nota.aluno.pessoa.nome}">
                                    <td><input type="text" name="nota" placeholder="00.00"
                                               class="form-control" value="${nota.nota}"
                                               style="width: 20.35%;"></td>
                                </c:when>
                                <c:otherwise>
                                    <td><input type="text" name="nota" placeholder="00.00"
                                               class="form-control" value="00.00"
                                               style="width: 20.35%;"></td>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                        </td>
                        <input type="hidden" value="${aluno.id}" name="aluno">
                        <input type="hidden" value="${turma.id}" name="turma">
                        <input type="hidden" value="${disciplina.id}" name="disciplina">

                    </tr>

                </c:forEach>
                </tbody>
            </table>
        </form>
    </div>


</div>
</body>
</html>

<% session.setAttribute("campoNota", null); %>