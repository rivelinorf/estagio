<%@ page import="br.com.sonner.estagio.model.Aluno" %>
<%@ page import="br.com.sonner.estagio.vos.TurmaFiltroVO" %>
<%@ page import="br.com.sonner.estagio.vos.EscolaFiltroVO" %>
<%@page contentType="text/html; charset=iso-8859-1"
        pageEncoding="iso-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="alunoController"
             class="br.com.sonner.estagio.controller.AlunoControllerImpl"></jsp:useBean>
<jsp:useBean id="turmaController"
             class="br.com.sonner.estagio.controller.TurmaControllerImpl"></jsp:useBean>
<jsp:useBean id="escolaController"
             class="br.com.sonner.estagio.controller.EscolaControllerImpl"></jsp:useBean>


<%
    Aluno campoAluno = (Aluno) session.getAttribute("campoAluno");

    if (campoAluno == null) {
        campoAluno = new Aluno();
        campoAluno.setPessoa(null);
        campoAluno.setNotas(null);
        campoAluno.setMatricula(null);
    }
%>

<%
    TurmaFiltroVO turmaFiltroVO = (TurmaFiltroVO) session.getAttribute("filtroTurma");
    if (turmaFiltroVO == null) {
        turmaFiltroVO = new TurmaFiltroVO();
        turmaFiltroVO.setNome("");
        turmaFiltroVO.setEscola(null);
    }
%>

<%
    EscolaFiltroVO escolaFiltroVO = (EscolaFiltroVO) session.getAttribute("filtroEscola");
    if (escolaFiltroVO == null) {
        escolaFiltroVO = new EscolaFiltroVO();
        escolaFiltroVO.setNome("");
        escolaFiltroVO.setEndereco(null);
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
    <sge:header titulo="Inserir novo Aluno" actionSalvar="true"
                formId="insere-form" actionFechar="true">
    </sge:header>
    <div class="div-form">
        <form action="/insere-aluno" method="post" id="insere-form"
              style="width: 60%; margin: auto">
            <jsp:include page="/includes/pessoaComponete.jsp"></jsp:include>
            <div class="form-row">
                <div>Número de matricula:</div>
                <input type="text"
                <%--value="<%= campoAluno.getMatricula() %>"--%>
                       name="matricula"
                       placeholder="Numero da matricula vai brota aqui" class="form-control"
                       maxlength="9">
            </div>

            <div class="form-row">
                <div>Escola:</div>
                <select name="escola" class="form-control" style="width: 61.35%;">
                    <option value="">Selecione uma opção...</option>
                    <c:forEach items="${escolaController.all}" var="escola">
                        <c:choose>
                            <c:when test="${escola.id == filtroEscola.escola}">
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
                <select name="turma" class="form-control" style="width: 61.35%;">
                    <option value="">Selecione uma opção...</option>
                    <c:forEach items="${turmaController.all}" var="turma">
                        <c:choose>
                            <c:when test="${turma.id == filtroTurma.turma}">
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
</div>
</body>
</html>
<% session.setAttribute("campoAluno", null); %>